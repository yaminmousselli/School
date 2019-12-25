// **** Your JavaScript code goes here ****
var svg = d3.select('svg');

var svgWidth = +svg.attr('width');
var svgHeight = +svg.attr('height');

var padding = {
    top: 20,
    right: 20,
    bottom: 60,
    left: 60
};

trellisWidth = svgWidth / 2 - padding.left - padding.right;
trellisHeight = svgHeight / 2 - padding.top - padding.bottom;

svg.selectAll('.background')
    .data(['A', 'B', 'C', 'D']) // dummy data
    .enter()
    .append('rect') // Append 4 rectangles
    .attr('class', 'background')
    .attr('width', trellisWidth) // Use our trellis dimensions
    .attr('height', trellisHeight)
    .attr('transform', function(d, i) {
        // Position based on the matrix array indices.
        // i = 1 for column 1, row 0)
        var tx = (i % 2) * (trellisWidth + padding.left + padding.right) + padding.left;
        var ty = Math.floor(i / 2) * (trellisHeight + padding.top + padding.bottom) + padding.top;
        return 'translate('+[tx, ty]+')';
});

// am i using the correct format specifiers? I couldn't find what %y is
var parseDate = d3.timeParse('%b %Y');

//check if bounds for both domains are correct or if I should use d3.max()
//can I have these inside callback?
//is it okay if I have an extra tick for the year 2020 on x-axis?
// when i change it to 2000, my scale messes up, why?
var dateDomain = [new Date(1875, 0), new Date(2020, 0)];
var priceDomain = [0, 4600];


d3.csv('./data/real_estate.csv', function(error, dataset) {
    if(error) {
        console.error('Error while loading ./data/real_estate.csv dataset.');
        console.error(error);
        return; // return gracefully
    }
real_estate = dataset;
//The attributes are strings in this dataset
console.log(real_estate);

/*You need to do this to parse the string date attributes into Date objects (JSON)
so we can use them to create a timescale */
real_estate.forEach(function(row) {
    row.date = parseDate(row.date);
});

//The attributes are JSON objects in this dataset
console.log(real_estate);

var nestedLocations = d3.nest()
    .key(function(r) {
    	// Returns 'Technology', 'Food & Drink', or 'Airlines'
        return r.location;
    })
    .entries(real_estate); // entries returns a key-value array

var trellisG = svg.selectAll('.trellis')
    .data(nestedLocations)
    .enter()
    .append('g')
    .attr('class', 'trellis')
    .attr('transform', function(d, i){
       var tx = (i % 2) * (trellisWidth + padding.left + padding.right) + padding.left;
       var ty = Math.floor(i / 2) * (trellisHeight + padding.top + padding.bottom) + padding.top;
       return 'translate('+[tx, ty]+')';
     });

var xScale = d3.scaleTime()
    .domain(dateDomain)
    .range([0, trellisWidth]);

var yScale = d3.scaleLinear()
    .domain(priceDomain)
    .range([trellisHeight, 0]);

//SCATTER PLOT TIME

// var numBedrooms = nestedLocations.map(function(d) {
//         return d.beds;
//     });
//     console.log(nestedLocations);

trellisG.selectAll('.mycircle')
    .data(function(d) {
        return d.values;
    })
    .enter()
    .append('circle')
    .attr('cx', function(d) {
        //must specify a new date object for each point because that's how I defined my scale. Linear scale is done differently
        return xScale(new Date(d.year_built));
    })
    .attr('cy', function(d) {
        return yScale(d.price_per_sqft);
    })
    .attr('r', 2)
    .attr('fill', function(d) {
        // you don't need the variable, just return ""#499936" and return "2e5d90"
        var filler = 0;
        if (d.beds <= 2) {
            filler = "#499936";
            return filler;
        } else {
            filler = "#2e5d90";
            return filler;
        }
    });

//everything here is fine.
var xAxis = d3.axisBottom(xScale);
trellisG.append('g')
    .attr('class', 'x axis')
    .attr('transform', 'translate(0,'+trellisHeight+')')
    .call(xAxis);

var yAxis = d3.axisLeft(yScale);
trellisG.append('g')
    .attr('class', 'y axis')
    .attr('transform', 'translate(0,0)')
    .call(yAxis);

trellisG.append('text')
    .attr('class', 'location-label')
    .attr('transform', 'translate('+[(trellisWidth / 2) - 40, 30]+')')
    .attr('class', 'caps')
    .text(function(d) {
        return d.key;
    });

// modify axis labels here for extra credit
trellisG.append('text')
    .attr('class', 'x axis-label')
    .attr('transform', 'translate('+[(trellisWidth / 2) - 30, trellisHeight + 34]+')')
    .text(function(d){
        var city = d.key;
        if (city === "Boston") {
            // console.log(typeof(city));
            // console.log("New York");
            return "Year Built";
        } else if (city === "Philadelphia") {
            return "Year Built";
        } else {
            return "";
        }
    });
trellisG.append('text')
    .attr('class', 'y axis-label')
    .attr('transform', 'translate('+[-42, (trellisHeight / 2) + 100]+') rotate(270)')
    .text(function(d) {
        var city = d.key;
        //JAVSCRIPTS OR OPERATION IS |, NOT ||
        if (city === "New York") {
            // console.log(typeof(city));
            // console.log("New York");
            return 'Price per Square Foot (USD)';
        } else if (city === "San Francisco") {
            return 'Price per Square Foot (USD)';
        } else {
            return "";
        }
    });
//var removeXAxisLabel = svg.append('g').call(xAxis);
//removeXAxisLabel.selectAll('text').remove();
});
