// **** Your JavaScript code goes here ****

// Activity 0.1: Load the dataset
// Activity 0.3: Create your scales
// Activity 0.2: Append circle for each Exoplanet and add attribute class 'planet'
// Activity 0.4: Set attributes of circles: cx, cy, and r. Fill the circle

d3.csv('./exoplanets.csv', function(error, dataset){
    if(error) {
        console.log('Error loading file: exoplanets.csv');
        console.error(error);
        return;
    }
    // x-scale
    var hzdExtent = d3.extent(dataset, function(d) {
        return +d['habital_zone_distance'];
    });
    var xScale = d3.scaleLinear()
        .domain(hzdExtent)
        .range([100,500]);

    // y-scale
    var massExtent = d3.extent(dataset, function(d) {
        return +d['mass'];
    });
    var yScale = d3.scaleLog()
        .domain(massExtent)
        .range([60,660]);

    // r-scale
    var radiusExtent = d3.extent(dataset, function(d) {
        return +d['radius'];
    });
    var rScale = d3.scaleSqrt()
        .domain(radiusExtent)
        .range([0,20]);

    // color-scale
    var colorExtent = d3.extent(dataset, function(d) {
        return +d['habital_zone_distance'];
    });
    var colorScale = d3.scaleQuantize()
        .domain(colorExtent)
        .range(['#d64d3f', '#96ac3d', '#208d8d']);

    var exoplanets = d3.select('svg');

    exoplanets.selectAll('circle')
        .data(dataset)
        .enter()
        .append('circle')
        //you need the + for negative entries. it converts strings to ints
        .attr('cx', function(d) {
            return xScale(+d['habital_zone_distance']);
        })
        .attr('cy', function(d) {
            return yScale(+d['mass']);
        })
        .attr('r',function(d){
            return rScale(+d['radius']);
        })
        .attr('fill', function(d) {
            return colorScale(+d.habital_zone_distance)
        })
        .attr('class', 'planet');

// Activity 1.1 & 1.2: Create x and y axes, then append and call your axes
    /*var bottomXAxis = d3.axisBottom(xScale);
    var topXAxis = d3.axisTop(xScale);
    var leftYAxis = d3.axisLeft(yScale);
    var rightYAxis = d3.axisRight(yScale);*/

    var svg = d3.select('svg');

    svg.append('g')
        .attr('class', 'bottomXAxis')
        .attr('transform', 'translate(0,680)')
        .style('fill', 'white')
        .call(bottomXAxis);

    svg.append('g')
        .attr('class', 'topXAxis')
        .attr('transform', 'translate(0,45)')
        .style('fill', 'white')
        .call(topXAxis);

    svg.append('g')
        .attr('class', 'leftYAxis')
        .attr('transform', 'translate(75, 0)')
        .style('fill', 'white')
        .call(leftYAxis);

    svg.append('g')
        .attr('class', 'rightYAxis')
        .attr('transform', 'translate(525, 0)')
        .style('fill', 'white')
        .call(rightYAxis);

// Activity 1.3: Append labels to x and y axis
    svg.append('text')
        .attr('class', 'Xaxis-label')
        .attr('transform', 'translate(225,715)')
        .text('Habitual Zone Distance')
        .style('fill', 'white');

    svg.append('text')
        .attr('class', 'topXAxis label')
        .attr('transform', 'translate(225,20)')
        .text('Habitual Zone Distance')
        .style('fill', 'white');

    svg.append('text')
        .attr('class', 'leftYAxis label')
        .attr('transform', 'translate(25, 500) rotate(270)')
        .text('Planet Mass (relative to Earth)')
        .style('fill', 'white');

    svg.append('text')
        .attr('class', 'rightYAxis label')
        .attr('transform', 'translate(585, 500) rotate(270)')
        .text('Planet Mass (relative to Earth)')
        .style('fill', 'white');
});
// Challenge 1: Add text label for following nodes: Earth, Mars, Mercury, Venus
