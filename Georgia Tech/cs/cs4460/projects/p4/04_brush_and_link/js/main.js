// Color mapping based on year
var yearColors = {2000: '#8c8c8c', 2010: '#d86763'};
var valueColors = ['#fcc9b5','#fa8873','#d44951','#843540'];

var svg = d3.select('svg');
var svgWidth = +svg.attr('width');
var svgHeight= +svg.attr('height');

var landColors = d3.scaleThreshold()
				   .domain([200, 500, 1000])
				   .range(valueColors);

var populationColors = d3.scaleThreshold()
						 .domain([500000, 1000000, 5000000])
						 .range(valueColors);

var urbanDensityColors = d3.scaleThreshold()
						   .domain([5000,10000,15000])
						   .range(valueColors);

var bins_density;
var bins_population;
var bins_lands;

var density_horizontal;
var density_population;
var density_lands;

var csvAttributes = ["density_growth", "pop_growth", "land_growth"];

var legend = valueColors.map(function(color) { return {legend:populationColors.invertExtent(color), color: color};}).reverse();


var tPadding = {
	t:40,
	r:50,
	l:30};

var title = d3.select('title');
var tWidth = +title.attr('width');
var tHeight = +title.attr('height');

var cell = {
	begin: 310,
	padding: 50,
	len:(960 / 3)};

var cellBrush;

var xScale = d3.scaleLinear()
			   .range([0, cell.len]);

var chartG = svg.append("g")
				.attr("transform", "translate(" + [cell.begin, 0] + ")");

var bHeight = 360;
var bWidth = (3/4 * cell.len);

var histo_width = cell.len;
var histo_height = svgHeight - cell.padding;

var histoExtent = {};
var barExtent = {};
var barDatum = {}

var brush = d3.brushX()
	.extent([[0, 0], [cell.len + 10, svgHeight - cell.padding]])
	.on("start", startBrush)
	.on("brush", moveBrush)
	.on("end", stopBrush);function highlight(country) {
	if (country == null) {
		d3.selectAll(".circle").classed("dontShow", false);
	} else {
		country = country.replace(/\s/g, "") ;
		d3.selectAll(".circle").classed("dontShow", true);
		d3.selectAll(".circle." + country).classed("dontShow", false);
	}
}

function startBrush(cell) {
    if(cellBrush !== this) {
        brush.move(d3.select(cellBrush), null);
        xScale.domain(histoExtent[cell.x]);
        cellBrush = this;
    }
}

function moveBrush(cell) {
    var event = d3.event.selection;
    if(event) {
        svg.selectAll(".circle")
            .classed("dontShow", function(d){
                return event[0] > xScale(d[cell.x]) || xScale(d[cell.x]) > event[1];
            })
    }
}

function stopBrush() {
	 if(!d3.event.selection) {
        svg.selectAll('.dontShow').classed('dontShow', false);
        cellBrush = undefined;
    }
}

function cell_histo(x, y, col) {
	this.col = col;
    this.x = x;
    this.y = y;
}

function cell_bar(x, col) {
	this.col = col;
    this.x = x;
}

cell_histo.prototype.init = function(g, dataset) {
    var cell = d3.select(g);

    cell.append("rect")
        .attr("class", "frame")
        .attr("width", histo_width)
        .attr("height", histo_height)
        .style("fill", "none");
}

cell_histo.prototype.update = function(g, dataset) {
	xScale.domain(histoExtent[this.x]);
	var cell = d3.select(g);
    var splom_cell = this;

	    if (splom_cell.x == "density_growth") {
	       dotHistogram = bins_density;
	    } else if (splom_cell.x == "pop_growth") {
	    	dotHistogram = bins_population;
	    } else if (splom_cell.x == "land_growth") {
	    	dotHistogram = bins_lands;
	    }

	var bins = cell.selectAll(".bin")
        			.data(dotHistogram);

	var insert_bins = bins.enter()
        				.append("g")
        				.attr("class", "bin")
				        .attr("transform", function(d) {
				            return "translate(" + xScale(d.x0) + "," + 670 + ")";
				        });

    var dots = insert_bins.selectAll(".dot")
				        .data(function(d) {
				            return d.sort(function(a,b) {
				                if (splom_cell.x == "density_growth") {
				                    return b.density_2010-a.density_2010;
				                }else if (splom_cell.x == "pop_growth") {
				                    return b.pop_2010-a.pop_2010;
				                }else if (splom_cell.x == "land_growth") {
				                    return b.land_2010-a.land_2010;
				                }
				            })
				        });

    var dotsEnter = dots.enter()
				        .append("circle")
				        .attr("class", function(d) {
				            return "circle " + (d.country).replace(/\s/g, "");
				        })
				        .attr("cx", 1)
				        .attr("cy", function(d, i) {
				            return -i*4;
				        })
				        .attr("r", 2)
				        .attr("fill", function(d) {
				            if (splom_cell.x == "density_growth") {
				                return urbanDensityColors(d.density_2010);
				            } else if (splom_cell.x == "pop_growth") {
				                return populationColors(d.pop_2010);
				            } else if (splom_cell.x == "land_growth") {
				                return landColors(d.land_2010);
				            }
				        })

    dots.merge(dotsEnter);

    dots.exit().remove();

}

cell_bar.prototype.init = function(g, dataset) {
    var bars = d3.select(g);

    bars.append("rect")
        .attr("class", "barGraphFrame")
        .attr("width", bWidth)
        .attr("height", bHeight)
        .style("fill", "white")
        .style("fill-opacity", "0.8")
        .style("stroke", "black");
}

cell_bar.prototype.update = function(g, dataset) {
    var bars = d3.select(g);

    var splom_cell = this;

    var labelBars = bars.selectAll(".labelBars")
				        .data(barDatum[this.x])
				        .enter()
				        .append("g")
				        .attr("class", function(d) {
				            return "circle " + (d.key).replace(/\s/g, "") ;
				        })
				        .attr("transform", function(d, i) {
				            var x = 75;
				            var y = 300 -i* 15
				            return "translate(" + x +"," + y +")";
				        })
				        .on("mouseover", function(d) {
				            highlight(d.key);
				        })
				        .on("mouseout", function(d) {
				            highlight(null);
				        });

    labelBars.append("text")
	         .attr("text-anchor", "end")
	         .attr("transform", "translate(5, 0)")
	         .text(function(d) {
	            return d.key;
	         })
	         .style("font-weight", "bold")
	         .style("font-size", "9px");

	 // 2010 bars
	 labelBars.append("rect")
			  .attr("x", 7)
			  .attr("y", -3)
			  .attr("height", 5)
			  .attr("width", function(d) {
			 	 if (splom_cell.x == "density_growth") {
			 	 	 return density_horizontal(d.value["2010"]);
			 	 } else if(splom_cell.x == "pop_growth") {
			 		 return density_population(d.value["2010"]);
			 	 } else if (splom_cell.x == "land_growth") {
			 		 return density_lands(d.value["2010"]);
			 	 }
			  })
			  .attr("fill", yearColors["2010"]);

    // 2000 bars
    labelBars.append("rect")
	         .attr("x", 7)
	         .attr("y", -8)
	         .attr("height", 5)
	         .attr("width", function(d) {
	            if (splom_cell.x == "density_growth") {
	                return density_horizontal(d.value["2000"]);
	            } else if(splom_cell.x == "pop_growth") {
	                return density_population(d.value["2000"]);
	            } else if (splom_cell.x == "land_growth") {
	                return density_lands(d.value["2000"]);
	            }
	        })
	         .attr("fill", yearColors["2000"]);

}
d3.csv('./data/asia_urbanization.csv',

function(row){
    return {
        city: row.city,
        country: row.country,
        type_country: row.type_country,
        land_2000: +row.land_2000,
        land_2010: +row.land_2010,
        land_growth: +row.land_growth,
        pop_2000: +row.pop_2000,
        pop_2010: +row.pop_2010,
        pop_growth: +row.pop_growth,
        density_2000: +row.density_2000,
        density_2010: +row.density_2010,
        density_growth: +row.density_growth
    }
},
function(error, dataset){
    if(error) {
        console.error('Error while loading ./data/asia_urbanization.csv dataset.');
        console.error(error);
        return;
    }
    // **** Your JavaScript code goes here ****

	csvAttributes.forEach(function(attribute) {
    	histoExtent[attribute] = d3.extent(dataset, function(d) {
    		return d[attribute];
    	});
    });

	var land_minimum = d3.min(dataset, function(d) { return d.land_growth});
	var population_minimum = d3.min(dataset, function(d) { return d.pop_growth});
	var density_minimum = d3.min(dataset, function(d) { return d.density_growth});


	var land_maximum = d3.max(dataset, function(d) { return d.land_growth});
    var population_maximum = d3.max(dataset, function(d) { return d.pop_growth});
    var density_maximum = d3.max(dataset, function(d) { return d.density_growth});


	var land_urban = d3.nest()
						.key(function(d) {
							return d.country;
						}).rollup(function(v) { return {
							2000: d3.sum(v, function(d) { return d.land_2000}),
							2010: d3.sum(v, function(d) { return d.land_2010})
							};
						})
						.entries(dataset);
	land_urban.sort(function(a, b) {
        return d3.ascending(a.value["2010"],b.value["2010"]);
        });
	barDatum["land_growth"] = land_urban;

	var population_urban = d3.nest()
					        .key(function(d) {
					            return d.country;
					        }).rollup(function(v) { return {
					            2000: d3.sum(v, function(d) { return d.pop_2000}),
					            2010: d3.sum(v, function(d) { return d.pop_2010})
					            };
					        })
					        .entries(dataset);

	population_urban.sort(function(a, b) {
		return d3.ascending(a.value["2010"],b.value["2010"]);
		});

	barDatum["pop_growth"] = population_urban;


    var density_average = d3.nest()
					        .key(function(d) {
					            return d.country;
					        }).rollup(function(v) { return {
					            2000: d3.mean(v, function(d) { return d.density_2000}),
					            2010: d3.mean(v, function(d) { return d.density_2010})
					            };
					        })
					        .entries(dataset);

	density_average.sort(function(a, b) {
        return d3.ascending(a.value["2010"],b.value["2010"]);
        });
	barDatum["density_growth"] = density_average;


	var second_land_minimum = d3.min(land_urban, function(d) { return d.value["2000"]});
	var second_population_minimum = d3.min(population_urban, function(d) { return d.value["2000"]});
    var second_density_minimum = d3.min(density_average, function(d) { return d.value["2000"]});

	var second_land_maximum = d3.max(land_urban, function(d) { return d.value["2010"]});
	var second_density_maximum= d3.max(density_average, function(d) { return d.value["2010"]});
    var second_population_maximum = d3.max(population_urban, function(d) { return d.value["2010"]});

    var start = cell.begin;

    var xDen = d3.scaleLinear()
		         .domain([density_minimum, density_maximum])
				 .range([start,(start + cell.len)]);

	start += cell.len + cell.padding;

	var second_start = start;

    var horizontal_population = d3.scaleLinear()
        						  .domain([population_minimum, population_maximum])
        						  .range([start, (start + cell.len)]);

    start += cell.len + cell.padding;

	var horizontal_land = d3.scaleLinear()
					        .domain([land_minimum, land_maximum])
					        .range([start, (start + cell.len)]);

    var second_horizontal_density = d3.scaleLinear()
								        .domain([0, second_density_maximum])
								        .range([0, cell.len / 2 - 10]);

	var second_horizontal_population = d3.scaleLinear()
								        .domain([0, second_population_maximum])
								        .range([0, cell.len / 2 - 10]);

	var second_horizontal_land = d3.scaleLinear()
							        .domain([0, second_land_maximum])
							        .range([0, cell.len / 2 - 10]);

	var bins_l = d3.histogram(horizontal_land)
			        .value(function(d) {
			            return d.land_growth;})
			        .domain(horizontal_land.domain())
			        .thresholds(horizontal_land.ticks(80))
			        (dataset);

	var bins_p = d3.histogram(horizontal_population)
			        .value(function(d) {
			            return d.pop_growth;})
			        .domain(horizontal_population.domain())
			        .thresholds(horizontal_population.ticks(80))
			        (dataset);

    var bins_d = d3.histogram(xDen)
			        .value(function(d) {
			            return d.density_growth;})
			        .domain(xDen.domain())
			        .thresholds(xDen.ticks(80))
			        (dataset);


	density_lands = second_horizontal_land;
	density_population = second_horizontal_population;
	density_horizontal = second_horizontal_density;

	bins_lands = bins_l;
	bins_population = bins_p;
	bins_density = bins_d;

	var y = d3.scaleLinear()
		       .domain([0, d3.max(bins_l, function(d) { return d.length; })])
		       .range([svgHeight - cell.padding, 0]);






	chartG.append('g')
          .attr('class', 'grid')
          .attr('transform', 'translate(-310,670)')
          .call(d3.axisBottom(xDen)
          .ticks(7)
          .tickFormat(d3.format(".0%")));

    chartG.append('g')
          .attr('class', 'yAxis')
          .attr('transform', 'translate(0,0)')
          .call(d3.axisLeft(y)
          .tickSize(-(cell.len), 0, 0));

	chartG.append('text')
          .attr('class', 'Yaxis-label')
          .attr('transform', 'translate(-15, 660) rotate(270)')
          .text('Number of Cities');

	chartG.append('text')
          .attr('class', 'title')
          .attr('transform', 'translate(128, 430)')
          .style('font-size', "14px")
          .style('font-weight', "bold")
          .text('Growth in avg. population density');

	chartG.append('text')
            .attr('class', 'title')
            .attr('transform', 'translate(128, 444)')
            .style('font-size', "14px")
            .style('font-weight', "bold")
            .text('between 2000 and 2010');

    chartG.append('g')
	        .attr('class', 'grid')
	        .attr('transform', 'translate(-310,670)')
	        .call(d3.axisBottom(horizontal_population)
	        .ticks(7)
	        .tickFormat(d3.format(".0%")));

	chartG.append('g')
	        .attr('class', 'yAxis')
	        .attr('transform', 'translate(' + 360 + ',0)')
	        .call(d3.axisLeft(y)
	        .tickSize(-(cell.len), 0, 0));

	chartG.append('text')
            .attr('class', 'Yaxis-label')
            .attr('transform', 'translate(' + 345 + ', 660) rotate(270)')
            .text('Number of Cities');

	chartG.append('text')
            .attr('class', 'title')
            .attr('transform', 'translate(478, 430)')
            .style('font-size', "14px")
            .style('font-weight', "bold")
            .text('Growth in population');

	chartG.append('text')
            .attr('class', 'title')
            .attr('transform', 'translate(478, 444)')
            .style('font-size', "14px")
            .style('font-weight', "bold")
            .text('between 2000 and 2010');

	chartG.append('g')
	        .attr('class', 'grid')
	        .attr('transform', 'translate(-310,670)')
	        .call(d3.axisBottom(horizontal_land)
	        .ticks(4)
	        .tickFormat(d3.format(".0%")));

	chartG.append('g')
	        .attr('class', 'yAxis')
	        .attr('transform', 'translate(' + (start-310) + ',0)')
	        .call(d3.axisLeft(y)
	        .tickSize(-(cell.len), 0, 0));

	chartG.append('text')
            .attr('class', 'Yaxis-label')
            .attr('transform', 'translate(' + (start-325) + ', 660) rotate(270)')
            .text('Number of Cities');

	chartG.append('text')
            .attr('class', 'title')
            .attr('transform', 'translate(858, 430)')
            .style('font-size', "14px")
            .style('font-weight', "bold")
            .text('Growth in urban land');

	chartG.append('text')
            .attr('class', 'title')
            .attr('transform', 'translate(858, 444)')
            .style('font-size', "14px")
            .style('font-weight', "bold")
            .text('between 2000 and 2010');







   var dotties = [];

	csvAttributes.forEach(function(attrX, col) {
	    dotties.push(new cell_histo(attrX, "num_cities", col));
	});

    var enter_dotties = chartG.selectAll(".cell")
						        .data(dotties)
						        .enter()
						        .append("g")
						        .attr("class", "cell")
						        .attr("transform", function(d, i) {
						            var tx = i * (histo_width + cell.padding);
						            var ty = 0;
						            return "translate("+[tx, ty]+")";
						        });

    enter_dotties.append('g')
        	.attr('class', 'brush')
        	.call(brush);

    enter_dotties.each(function(cell){
        cell.init(this);
        cell.update(this, dataset);
    });

	var bars_for_dotties = [];

	csvAttributes.forEach(function(attrX, col) {
    	bars_for_dotties.push(new cell_bar(attrX, col));
    });

 	var enter_the_dotties = chartG.selectAll(".cell_bar")
    	.data(bars_for_dotties)
    	.enter()
    	.append("g")
    	.attr("class", "cell_bar")
    	.attr("transform", function(d, i) {
    		var tx = i*(cell.len + cell.padding) + cell.len/4;
    		var ty = 20;
    		return "translate("+[tx, ty]+")";
    	});

    enter_the_dotties.each(function(cell_bar){
        cell_bar.init(this);
        cell_bar.update(this, dataset);
		});




	chartG.selectAll(".xAxis")
	    	.data(csvAttributes)
	    	.enter()
	    	.append("g")
	    	.attr("class", "x axis")
	    	.attr("transform", function(d,i) {
	    		return "translate(" + [i * (50 + cell.len) + (cell.len / 4 + 82), 330] + ")";
	    	}).each(function(attribute) {
	    		var scale;
	    		var text;
	        	if (attribute == "density_growth") {
	        		scale = density_horizontal;
	        		text = "Avg. population density (in persons/Km²)";
	        	} else if (attribute == "pop_growth"){
	        		scale = density_population;
	        		text = "Urban Population";
	        	} else if (attribute == "land_growth") {
	        		scale = density_lands;
	        		text = "Urban land (in Km²)";
	        	}
	        	var axis = d3.axisBottom(scale);
	        	axis.ticks(4)
	        	axis.tickFormat(d3.format(".0s"))
	        	d3.select(this).call(axis);
	    	})





	var first_key = svg.selectAll(".first_key")
				        .data(legend)
				        .enter()
				        .append("g")
				        .attr("transform", "translate(550, 520)")

    first_key.append("rect")
	        .attr("x", 0)
	        .attr("y", function(d,i) {
	            return i*15
	        })
	        .attr("width", 15)
	        .attr("height", 15)
	        .style("fill", function(d) { return d.color});

	first_key.append("text")
	        .attr("x", 20)
	        .attr("y", function(d,i) {
	            return i*15 + 11
	        })
	        .style("font-size", "12px")
	        .style("font-weight", "bold")
	        .text(function(d,i) {
	        	switch(i) {
	        		case 3:
	        			return "< 5k";
	        		case 2:
	        			return "5k - 10k";
	        		case 1:
	        			return "10k - 15k";
	        		case 0:
	        			return "> 15k";
	        	}
	        })

	first_key.append("text")
	    	.attr("x", -40)
	    	.attr("y", -5)
	    	.style("font-size", "14px")
	    	.text("Urban density - 2010")






	var second_key = svg.selectAll(".second_key")
        .data(legend)
        .enter()
        .append("g")
        .attr("transform", "translate(900, 520)")

    second_key.append("rect")
		        .attr("x", 0)
		        .attr("y", function(d,i) {
		            return 15 * i
		        })
		        .attr("width", 15)
		        .attr("height", 15)
		        .style("fill", function(d) { return d.color});

	second_key.append("text")
	        .attr("x", 20)
	        .attr("y", function(d,i) {
	            return 15 * i + 11
	        })
	        .style("font-size", "12px")
	        .style("font-weight", "bold")
	        .text(function(d,i) {
	        	switch(i) {
	        		case 3:
	        			return "< 2M";
	        		case 2:
	        			return "2M - 3M";
	        		case 1:
	        			return "3M - 4M";
	        		case 0:
	        			return "> 4M";
	        	}
	        })

	second_key.append("text")
		    	.attr("x", -40)
		    	.attr("y", -5)
		    	.style("font-size", "14px")
		    	.text("Urban population - 2010")






    var third_key = svg.selectAll(".third_key")
				        .data(legend)
				        .enter()
				        .append("g")
				        .attr("transform", "translate(1250, 520)")

    third_key.append("rect")
        .attr("x", 0)
        .attr("y", function(d,i) {
            return 15 * i
        })
        .attr("width", 15)
        .attr("height", 15)
        .style("fill", function(d) { return d.color});


	third_key.append("text")
	        .attr("x", 20)
	        .attr("y", function(d,i) {
	            return 15 * i + 11
	        })
	        .style("font-size", "12px")
	        .style("font-weight", "bold")
	        .text(function(d,i) {
	        	switch(i) {
	        		case 3:
	        			return "< 0.4k";
	        		case 1:
	        			return "0.6k - 0.8k";
	        		case 2:
	        			return "0.4k - 0.6k";
	        		case 0:
	        			return "> 0.8k";
	        	}
	        })

    third_key.append("text")
	    	.attr("x", -40)
	    	.attr("y", -5)
	    	.style("font-size", "14px")
	    	.text("Urban land - 2010")
});
