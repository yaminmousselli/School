// First example
var svg = d3.select('svg');
var letter = svg.selectAll('.letter')
        .data(['A', 'B', 'C']);

// Enter
// Create spaced out <g> elements with classname letter
var letterEnter = letter.enter()
        .append('g')
        .attr('class', 'letter')
        .attr('transform', function(d, i) {
            // first num is how far circles are away from each other
            // second num is how far left or right
            // third num is how far up or down
          return 'translate('+[i * 30 + 50, 50]+')';
        });

// Append circles
letterEnter.append('circle')
        .attr('r', 10)
        .attr('fill', '#A9A9A9');

// Append text
letterEnter.append('text')
        .attr('x', -5)
        .attr('y', 30)
        .attr('fill', '#A9A9A9')
        .text(function(d) {
          return d;
        });

// Update: Uncomment to see effect
var letterCircle = d3.select('svg').selectAll('.letter circle');
letterCircle.attr('r', 15);

// Exit: Uncomment to see effect
var letter = svg.selectAll('.letter')
	.data(['A', 'B']);
letter.exit().remove();

// Create a function that does enter, update, exit to update our circles
function updateCircles(letters) {
    var letter = svg.selectAll('.letter')
        .data(letters);

    var letterEnter = letter.enter()
        .append('g')
        .attr('class', 'letter');

    letterEnter.merge(letter)
        .attr('transform', function(d,i) {
            return 'translate('+[i * 30 + 50, 50]+')';
        });

    letterEnter.append('circle')
        .attr('r', 15)
        .attr('fill', '#A9A9A9');

    letterEnter.append('text')
        .attr('x',-5)
        .attr('y', 30)
        .attr('fill', '#A9A9A9')
        .text(function(d) {
            return d;
        });

    letter.exit().remove();
}

// Uncomment each line (71 - 73) to see the results

updateCircles(['A', 'B', 'C']);
updateCircles(['A', 'B']);
updateCircles(['A', 'B', 'C', 'D', 'E', 'F']);


/*
 * Lab Stuff
 * In the Lab 6 activity, you will need to use the key function to retain object constancy in the bar
 * chart.
