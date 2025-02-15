var svg = d3.select('svg');

// Get layout parameters
var svgWidth = +svg.attr('width');
var svgHeight = +svg.attr('height');

var padding = {t: 40, r: 40, b: 40, l: 40};
var cellPadding = 10;

// Create a group element for appending chart elements
var chartG = svg.append('g')
    .attr('transform', 'translate('+[padding.l, padding.t]+')');

var dataAttributes = ['economy (mpg)', 'cylinders', 'power (hp)', '0-60 mph (s)'];
var N = dataAttributes.length;

// Compute chart dimensions
var cellWidth = (svgWidth - padding.l - padding.r) / N;
var cellHeight = (svgHeight - padding.t - padding.b) / N;

// Global x and y scales to be used for all SplomCells
var xScale = d3.scaleLinear().range([0, cellWidth - cellPadding]);
var yScale = d3.scaleLinear().range([cellHeight - cellPadding, 0]);
// axes that are rendered already for you
var xAxis = d3.axisTop(xScale).ticks(6).tickSize(-cellHeight * N, 0, 0);
var yAxis = d3.axisLeft(yScale).ticks(6).tickSize(-cellWidth * N, 0, 0);
// Ordinal color scale for cylinders color mapping
var colorScale = d3.scaleOrdinal(d3.schemeCategory10);
// Map for referencing min/max per each attribute
var extentByAttribute = {};
// Object for keeping state of which cell is currently being brushed
var brushCell;

// ****** Add reusable components here ****** //
function SplomCell(x, y, col, row) {
    this.x = x;
    this.y = y;
    this.col = col;
    this.row = row;
}

var brush = d3.brush()
    .extent([[0, 0], [cellWidth - cellPadding, cellHeight - cellPadding]])
    .on("start", brushstart)
    .on("brush", brushmove)
    .on("end", brushend);

var toolTip = d3.tip()
    .attr("class", "d3-tip")
    .offset([-12, 0])
    .html(function(d) {
        return "<h5>"+d['name']+"</h5><table><thead><tr><td>0-60 mph (s)</td><td>Power (hp)</td><td>Cylinders</td><td>Year</td></tr></thead>"
            + "<tbody><tr><td>"+d['0-60 mph (s)']+"</td><td>"+d['power (hp)']+"</td><td>"+d['cylinders']+"</td><td>"+d['year']+"</td></tr></tbody>"
            + "<thead><tr><td>Economy (mpg)</td><td colspan='2'>Displacement (cc)</td><td>Weight (lb)</td></tr></thead>"
            + "<tbody><tr><td>"+d['economy (mpg)']+"</td><td colspan='2'>"+d['displacement (cc)']+"</td><td>"+d['weight (lb)']+"</td></tr></tbody></table>"
    });

svg.call(toolTip);

d3.csv('./cars.csv',
    // Load data and use this function to process each row
    function(row) {
        return {
            'name': row['name'],
            'economy (mpg)': +row['economy (mpg)'],
            'cylinders': +row['cylinders'],
            'displacement (cc)': +row['displacement (cc)'],
            'power (hp)': +row['power (hp)'],
            'weight (lb)': +row['weight (lb)'],
            '0-60 mph (s)': +row['0-60 mph (s)'],
            'year': +row['year']
        };
    },
    function(error, dataset) {
        // Log and return from an error
        if(error) {
            console.error('Error while loading ./cars.csv dataset.');
            console.error(error);
            return;
        }

        cars = dataset;

        // Create map for each attribute's extent
        dataAttributes.forEach(function(attribute){
            extentByAttribute[attribute] = d3.extent(dataset, function(d){
                return d[attribute];
            });
        });

        // Pre-render gridlines and labels
        chartG.selectAll('.x.axis')
            .data(dataAttributes)
            .enter()
            .append('g')
            .attr('class', 'x axis')
            .attr('transform', function(d,i) {
                return 'translate('+[(N - i - 1) * cellWidth + cellPadding / 2, 0]+')';
            })
            .each(function(attribute){
                xScale.domain(extentByAttribute[attribute]);
                d3.select(this).call(xAxis);
                d3.select(this).append('text')
                    .text(attribute)
                    .attr('class', 'axis-label')
                    .attr('transform', 'translate('+[cellWidth / 2, -20]+')');
            });
        chartG.selectAll('.y.axis')
            .data(dataAttributes)
            .enter()
            .append('g')
            .attr('class', 'y axis')
            .attr('transform', function(d,i) {
                return 'translate('+[0, i * cellHeight + cellPadding / 2]+')';
            })
            .each(function(attribute){
                yScale.domain(extentByAttribute[attribute]);
                d3.select(this).call(yAxis);
                d3.select(this).append('text')
                    .text(attribute)
                    .attr('class', 'axis-label')
                    .attr('transform', 'translate('+[-26, cellHeight / 2]+')rotate(270)');
            });


        // ********* Your data dependent code goes here *********//
        var cells = [];
        dataAttributes.forEach(function(attrX, col){
            dataAttributes.forEach(function(attrY, row){
                cells.push(new SplomCell(attrX, attrY, col, row));
            });
        });

        SplomCell.prototype.init = function(g) {
            var cell = d3.select(g);

            cell.append('rect')
                .attr('class', 'frame')
                .attr('width', cellWidth - cellPadding)
                .attr('height', cellHeight - cellPadding);
        };

        SplomCell.prototype.update = function(g, data) {
            var cell = d3.select(g);

        xScale.domain(extentByAttribute[this.x]);
        yScale.domain(extentByAttribute[this.y]);

        var _this = this;

        var dots = cell.selectAll('.dot')
            .data(data, function(d){
                return d.name +'-'+d.year+'-'+d.cylinders; 
            });

        var dotsEnter = dots.enter()
            .append('circle')
            .attr('class', 'dot')
            .style("fill", function(d) { return colorScale(d.cylinders); })
            .attr('r', 4);




        dots.merge(dotsEnter).attr('cx', function(d){
            return xScale(d[_this.x]);
        })
            .attr('cy', function(d){
                return yScale(d[_this.y]);
            });

        dotsEnter.on('mouseover', toolTip.show)
            .on('mouseout', toolTip.hide);


        dots.exit().remove();
    };
        var cellEnter = chartG.selectAll('.cell')
            .data(cells)
            .enter()
            .append('g')
            .attr('class', 'cell')
            .attr("transform", function(d) {
                var tx = (N - d.col - 1) * cellWidth + cellPadding / 2;
                var ty = d.row * cellHeight + cellPadding / 2;
                return "translate("+[tx, ty]+")";
            });

        cellEnter.each(function(cell){
            cell.init(this);
            cell.update(this, dataset);
        });

        cellEnter.append('g')
            .attr('class', 'brush')
            .call(brush);



        cellEnter.append('g')
            .call(brush);

        cellEnter.each(function(c){
            c.init(this);
            c.update(this, dataset);
        });


    });

// ********* Your event listener functions go here *********//
function brushstart(cell) {

    if(brushCell !== this) {

        brush.move(d3.select(brushCell), null);

        xScale.domain(extentByAttribute[cell.x]);
        yScale.domain(extentByAttribute[cell.y]);

        brushCell = this;
    }
}

function brushmove(cell) {

var e = d3.event.selection;
if(e) {
    svg.selectAll(".dot")
        .classed("hidden", function(d){
            return e[0][0] > xScale(d[cell.x]) || xScale(d[cell.x]) > e[1][0]
                || e[0][1] > yScale(d[cell.y]) || yScale(d[cell.y]) > e[1][1];
        })
    }
}

function brushend() {
    if(!d3.event.selection) {
        svg.selectAll('.hidden').classed('hidden', false);
        brushCell = undefined;
    }
}

// Remember code outside of the data callback function will run before the data loads
