var coffeeData = [
    { month: "May", sales: 6900 },
    { month: "June", sales: 14240 },
    { month: "July", sales: 25000 },
    { month: "August", sales: 17500 }
];

var yScale = d3.scaleBand()
    .domain(['May', 'June', 'July', 'August'])
    .rangeRound([40,260])
    .padding(0.5);

var wScale = d3.scaleLinear()
    .domain([0, 25000])
    .range([0,300]);

var svg = d3.select('svg');

svg.selectAll('rect')
    .data(coffeeData)
    .enter()
    .append('rect')
    .attr('y', function(d){
        return yScale(d.month);
    })
    .attr('x', 80)
    .attr('height', yScale.bandwidth())
    .attr('width', function(d){
        return wScale(d.sales);
    })
    .style('fill', '#5f3e36');

var wAxis = d3.axisBottom(wScale).ticks(6);

svg.append('g')
    .attr('class', 'x axis')
    .attr('transform', 'translate(80,250)')
    .call(wAxis);

var yAxis = d3.axisLeft(yScale);

svg.append('g')
   .attr('class', 'y axis')
   .attr('transform', 'translate(70,0)')
   .call(yAxis);

svg.append('text')
   .attr('class', 'x label')
   .attr('transform', 'translate(170,290)')
   .text('Coffee Shop Sales ($)');

svg.append('text')
   .attr('class', 'y label')
   .attr('transform', 'translate(25,170)rotate(270)')
   .text('Months');
