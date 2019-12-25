

d3.csv('./grades.csv', 
  
  // formatting data
  function(row){
    return {
      name: row.name,
      year: row.year,
      grade: +row.grade,
    };
  },

  // processing data and draw graphs
  function(data) {
    console.log('Original data: \n', data);

    var svg = d3.select("svg"),
    margin = {top: 10, right: 30, bottom: 30, left: 30},
    width = +svg.attr("width") - margin.left - margin.right,
    height = +svg.attr("height") - margin.top - margin.bottom,
    chart = svg.append("g").attr("transform", "translate(" + margin.left + "," + margin.top + ")");

    // Compute range of grades, which is [61, 99]
    var gradeExtent = d3.extent(data, function(d) { return d.grade; }); 

    // Create a scale to map the domain [61, 99] to pixel values
    // It will be used for both positioning the bars and create the axis
    var xScale = d3.scaleLinear().range([0, width]).domain(gradeExtent);

    ////////////////////// Nothing new above

    // Now we use d3.histogram() to set up our data transformation
    var groupDataIntoBins = d3.histogram()
                              .value(function(d) { return d.grade; }) // tell d3 to group data by grades
                              .domain(xScale.domain()) // only include data within [61, 99], anything outside will be ignored
                              // .domain([70,85]) // try this and see what happens 
                              .thresholds(xScale.ticks(10)); // create about 10 ticks for xScale, and use the tick values as endpoints for bins.
    console.log('ticks: \n', xScale.ticks(10)); // It turns out the ticks created are [60, 65, 70, ...]
    

    // apply the data-transformation function we just set up to the actual data to group the data.
    var groupedGrades = groupDataIntoBins(data);
    console.log('groupedGrades: \n', groupedGrades); // check this out in the console!
    console.log('students in the first bin: \n', groupedGrades[0]); // An array of 3 students
    console.log('range of the first bin: \n', groupedGrades[0].x0, 'to', groupedGrades[0].x1); // 60 to 65


    var yScale = d3.scaleLinear()
                   .range([height, 0])
                   .domain([0, d3.max(groupedGrades, function(d) { return d.length; })])
    
    // Create a <g> element for each bin. Whatever you want to display in each bin (single bar or multiple circles)
    //    goes into the group 
    var barGroup = chart.selectAll('.barG')
                        .data(groupedGrades) // Remember that groupedGrades is an array with each element corresponding to a bin
                        .enter()
                        .append('g')
                        .attr('class', 'barG')
                        .attr('transform', function(d) { 
                          return 'translate(' + xScale(d.x0) + ',' + yScale(d.length) + ')';
                        })
    
    // Append a bar to each bin (the <g> element)
    //   - Since here we just need a single bar, we do not need to use nested selection. But you will need
    //   to do so for the homework since we need to create a circle for each data point nested inside.
    //   - Go back and check Lab5 and Homework 2 to refresh your memeory on nested selection 
    barGroup.append('rect')
            .attr('x', 1)
            .attr('width', function(d) {
              return xScale(d.x1) - xScale(d.x0) - 2;
            }) // Use the speicfic bin's width as the bar width. The first and last bins may be smaller.
            .attr('height', function(d) { return height - yScale(d.length) }) // d.length is the count
            .attr('fill', '#f0b503');


    // create the x-axis, nothing new
    chart.append('g')
         .attr('transform', 'translate(0,' + height + ')')
         .call(d3.axisBottom(xScale));
    
    chart.append('g')
         .attr('transform', 'translate(0,0)')
         .call(d3.axisLeft(yScale));

  }
);