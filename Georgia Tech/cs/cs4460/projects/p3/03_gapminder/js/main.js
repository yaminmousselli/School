// Creates a bootstrap-slider element
$("#yearSlider").slider({
    tooltip: 'always',
    tooltip_position:'bottom'
});
// Listens to the on "change" event for the slider
$("#yearSlider").on('change', function(event){
    // Update the chart on the new value
    updateChart(event.value.newValue);
});

/*function onCategoryChanged() {
    var select = d3.select('#categorySelect').node();
    console.log(select);
    console.log(select.selectedIndex);
    // Get current value of select element
    // Get value of continent
    var category = select.options[select.selectedIndex].value;
    console.log(category);
    // Update chart with the selected category of letters
    updateChart(category);
}*/

var svg = d3.select('svg');

// Get layout parameters
var svgWidth = +svg.attr('width');
var svgHeight = +svg.attr('height');

//this is how you shift the axes
var padding = {t: 60, r: 40, b: 60, l: 40};

var chartWidth = svgWidth - padding.l - padding.r;
var chartHeight = svgHeight - padding.t - padding.b;

// Create a group element for appending circle (bubble) elements
var chartG = svg.append('g')
    .attr('transform', 'translate('+[padding.l, padding.t]+')');

// Color mapping based on continents
var contintentColors = {Asia: '#fc5a74', Europe: '#fee633',
    Africa: '#24d5e8', Americas: '#82e92d', Oceania: '#fc5a74'};

d3.csv('./data/gapminder.csv',
    function(d){
        // This callback formats each row of the data
        return {
            country: d.country,
            year: +d.year,
            population: +d.population,
            continent: d.continent,
            lifeExp: +d.lifeExp,
            gdpPercap: +d.gdpPercap
        }
    },
    function(error, dataset){
        if(error) {
            console.error('Error while loading ./gapminder.csv dataset.');
            console.error(error);
            return;
        }

        // **** Set up your global variables and initialize the chart here ****
        countries = dataset;

        // var nestedYears = d3.nest()
        //     .key(function(c) {
        //         return c.country;
        //     })
        //     .object(countries);

        // x-scale
        // @Piazza, use these for domain
        var xMin = d3.min(countries, function(d) {
            return d.gdpPercap;
        });
        // xMin = 241.1658765
        console.log(xMin);

        var xMax = d3.max(countries, function(d) {
            return d.gdpPercap;
        });
        // xMax = 113523.1329
        console.log(xMax);

        // fuck the extent, i don't need it.
        /*
            var xExtent = d3.extent(countries, function(d) {
                return +d.gdpPercap;
            });
        */

        xScale = d3.scaleLog()
                   .domain([xMin, xMax])
                   .range([0, chartWidth]);

        // y-scale
        var yMin = d3.min(countries, function(d) {
            return d.lifeExp;
        });
        // yMin = 23.599
        console.log(yMin);

        var yMax = d3.max(countries, function(d) {
            return d.lifeExp;
        });
        // yMax = 82.603
        console.log(yMax);

        /*
        var yExtent = d3.extent(countries, function(d) {
            return +d.lifeExp;
        });
        */

        yScale = d3.scaleLinear()
                    .domain([yMin, yMax])
                    .range([chartHeight, 0]);

        // r-scale
        var rMax = d3.max(countries, function(d) {
            return d.population;
        });

        var rMin = d3.min(countries, function(d) {
            return d.population;
        });

        /*
        var rExtent = d3.extent(countries, function(d) {
            return +d.population;
        });
        */

        // @Piazza, must experiment with range.
        rScale = d3.scaleSqrt()
                   .domain([rMin, rMax])
                   .range([0, 40]);

        //Set up Axis and Labels
        var formatSpec = d3.format(".0f");
        var xAxis = d3.axisBottom(xScale)
                      .tickValues([500, 1000, 2000, 4000, 8000, 16000, 32000, 64000])
                      .tickSizeInner([0 - chartHeight])
                      .tickFormat(formatSpec);

        var yAxis = d3.axisLeft(yScale)
                      .tickSizeInner([0 - chartWidth])
                      .ticks(6);

        chartG.append('g')
            .attr('class', 'xAxis')
            .attr('transform', 'translate(0, '+ chartHeight +')')
            .attr('class', 'tick')
            .attr('class', 'legend')
            .call(xAxis);

        chartG.append('g')
            .attr('class', 'yAxis')
            .attr('class', 'tick')
            .attr('class', 'legend')
            .call(yAxis);

        chartG.append('text')
            .attr('class', 'Xaxis-label')
            .attr('transform', 'translate(0,625)')
            .text('Income per person, GDO/capita in $/year adjusted for inflation')
            .attr('class', 'legend');

        chartG.append('text')
            .attr('class', 'leftYAxis label')
            .attr('transform', 'translate(-25, -25)')
            .text('Life expectancy, years')
            .attr('class', 'legend');

        chartG.append('text')
            .attr('class', 'rightYAxis label')
            .attr('transform', 'translate(630, -25)')
            .text('GapMinder Countries')
            .style('font-weight', 'bold')
            .style('font-size', 30)
            .style('fill', '#ccc');

        updateChart(1952);
});

function updateChart(year, continent) {
        // **** Update the chart based on the year here ****
        var filtered_Countries = countries.filter(function(c) {
                return (c.year == year);
        });

        // // **** Draw and Update your chart here ****
        // var tool_tip = d3.tip()
        //      .attr("class", "d3-tip")
        //      .offset([-8, 0])
        //      .html(function(d) {
        //          return "Country: " + d.country;
        //      });
        //
        // svg.call(tool_tip);

        // Create an update selection
        var circles = chartG.selectAll('.circle')
            .data(filtered_Countries);
            // .on('mouseover', tool_tip.show)
            // .on('mouseout', tool_tip.hide);

        // Enter and append all new elements
        var circlesEnter = circles.enter()
            .append('circle')
            .attr('stroke', 'black')
            .style('fill', function(d) {
                return contintentColors[d.continent];
            });


        circles.merge(circlesEnter)
            .attr('cx', function(d) {
                return xScale(+d.gdpPercap);
            })
            .attr('cy', function(d) {
                return yScale(+d.lifeExp);
            })
            .attr('r',function(d){
                return rScale(+d.population);
            })
            .attr('class', 'circle')


        // Exit and remove filtered circles
        circles.exit().remove();
}
