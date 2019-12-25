// **** Your JavaScript code goes here ****
d3.csv('./data/coffee_data.csv', function(error, dataset) {
    if(error) {
        console.log('Error loading file: coffee_data.csv');
        console.error(error);
        return;
    }
    coffee_data = dataset;

    //use d3.nest() for Region
    //nest() creates an object with 2 keys and they are key (regio) and value (sales)
    var salesByRegion = d3.nest()
                        .key(function(d) {
                            return d['region'];
                        })
                        .rollup(function(v) {
                            var salesTotal = d3.sum(v, function(d) {
                                return d['sales'];
                            })
                            return salesTotal;
                        })
                        .entries(coffee_data);

    //check to see if it displays the dictionary for salesByRegion
    console.log(salesByRegion);

    // use d3.nest() for Product
    var salesByCategory = d3.nest()
                            .key(function(d) {
                                return d['category'];
                            })
                            .rollup(function(v) {
                                var salesTotal = d3.sum(v, function(d) {
                                    return d['sales'];
                                })
                                return salesTotal;
                            })
                            .entries(coffee_data);

    //check to see if it displays the dictionary (key-value) for salesByCategory
    console.log(salesByCategory);


    // Concatenate both arrays to find the maximum for the y-axis
    var combineArrays = salesByRegion.concat(salesByCategory);
    //check to if concatenation works
    console.log(combineArrays);

    //store maximum in variable
    var axisMax = d3.max(combineArrays, function(d) {
        return d['value'];
    });
    console.log(axisMax);


    //Create Scales for Chart 1 (Region)
    var margin = {
        top: 100,
        bottom: 40,
        left: 65,
        right: 35
    };
    var svg = d3.select('svg');
    //grabs value for svg width from index.html
    // Use + to convert string to integer
    // need to divide by 2 because we have to graphs
    var chartWidth = +svg.attr('width') / 2 - margin.left - margin.right;
    console.log(chartWidth);

    //don't need to divide by 2 because it's a 1 x 2
    var chartHeight = +svg.attr('height') - margin.top - margin.bottom;
    console.log(chartHeight);

    //create 1 group per graphs
    var regionGroup = svg.append('g');
    var categoryGroup = svg.append('g');

    //Transform and translate the groups to know where to place them
    regionGroup.attr('transform', 'translate('+[margin.left, margin.top] +')');
    categoryGroup.attr('transform',
        'translate('+[chartWidth + margin.left + margin.right, margin.top] +')');

    //yScale is the same for both histograms

    var yScale = d3.scaleLinear()
        .domain([0, axisMax])
        .range([0, chartHeight]);

    //you can call more than one fucking thing in your group objects.
    var yAxis = d3.axisLeft(yScale)
        .ticks(6)
        .tickFormat(function (d) {
            return "" + (d / 1000) + "K";
        });

    //create this for my domain
    var regionKey = salesByRegion.map(function(d) {
        return d.key;
    });
    console.log(regionKey);

    var xRegionScale = d3.scaleBand()
       .domain(regionKey)
       .range([0, chartWidth])
       .padding(0.3);

    var productKey = salesByCategory.map(function(d) {
       return d.key;
   });
    var xProductScale = d3.scaleBand()
        .domain(productKey) //fix width
        .range([0, chartWidth])
        .padding(0.3);

    //Do Bars for category chart
    //color based d3.schmeaCategory10 (google this)
    //quantizeScale
    console.log(salesByRegion);
    regionGroup.selectAll('.bar')
                .data(salesByRegion)
                .enter()
                .append('rect')
                .attr('class', 'bar')
                .attr('x', function(d) {
                    return xRegionScale(d.key);
                })
                .attr('y', function(d) {
                    //you have to do this to figure out y which is position
                    return chartHeight - yScale(d.value);
                })
                .attr('height', function(d) {
                    return yScale(d.value);
                })
                //bandwith() splits the bars into 4 uniform positions
                .attr('width', xRegionScale.bandwidth());

    categoryGroup.selectAll('.bar')
        .data(salesByCategory)
        .enter()
        .append('rect')
        .attr('class', 'bar')
        .attr('x', function(d) {
            return xProductScale(d.key) + 60; //hacked it
        })
        .attr('y', function(d) {
            //you have to do this to figure out y which is position
            return chartHeight - yScale(d.value);
        })
        .attr('height', function(d) {
            return yScale(d.value);
        })
        //bandwith() splits the bars into 4 uniform positions
        .attr('width', xProductScale.bandwidth());

    regionGroup.append('g')
        .attr('class', 'x axis')
        .attr('transform', 'translate('+[0, chartHeight] +')')
        .call(d3.axisBottom(xRegionScale));

        //on y axis call for
        //d3 reads from top to bottom, I need to switch the range when I call my axis
        // try 0 to height first to see what happens
    regionGroup.append('g')
        .attr('class', 'y axis')
        .attr('transform', 'translate(-5,0)')
        .call(d3.axisLeft(yScale.range([chartHeight, 0])))
        .call(yAxis);

    categoryGroup.append('g')
        .attr('class', 'x axis')
        .attr('transform', 'translate('+[60, chartHeight] +')')
        .call(d3.axisBottom(xProductScale));

        //on y axis call for
        //d3 reads from top to bottom, I need to switch the range when I call my axis
        // try 0 to height first to see what happens
    categoryGroup.append('g')
        .attr('class', 'y axis')
        .attr('transform', 'translate(52,0)')
        .call(d3.axisLeft(yScale.range([chartHeight, 0])))
        .call(yAxis);

    svg.append('text')
        .attr('class', 'bottomLeftXAxis label')
        .attr('transform', 'translate(180,592)')
        .text('Region');

    svg.append('text')
        .attr('class', 'bottomLeftXAxis label')
        .attr('transform', 'translate(560,592)')
        .text('Product');

    svg.append('text')
        .attr('class', 'leftYAxis label')
        .attr('transform', 'translate(15, 400) rotate(270)')
        .text('Coffee Sales (USD)');

    svg.append('text')
        .attr('class', 'leftYAxis label')
        .attr('transform', 'translate(387, 400) rotate(270)')
        .text('Coffee Sales (USD)');

    svg.append('text')
        .attr('class', 'topXAxis label')
        .attr('transform', 'translate(107,80)')
        .text('Coffee Sales by Region (USD)');

    svg.append('text')
        .attr('class', 'topXAxis label')
        .attr('transform', 'translate(485,80)')
        .text('Coffee Sales by Product (USD)');
});
