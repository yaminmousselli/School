d3.csv('./worldcup.csv', function(error, datum){
    if(error) {
        console.error(error);
        return;
    }

    var maxGA = d3.max(datum, function(d){return +d['Goals_Against'];});
    var maxGF = d3.max(datum, function(d){return +d['Goals_For'];});
    var maxPlayed = d3.max(datum, function(d){return +d['Played'];});

    var xScale = d3.scaleLinear().domain([0, maxGA]).range([60,360]);
    var yScale = d3.scaleLinear().domain([0, maxGF]).range([660,20]);
    var rScale = d3.scaleSqrt().domain([0, maxPlayed]).range([0,30]);

    var xAxis = d3.axisBottom(xScale);
    var yAxis = d3.axisLeft(yScale);

    var svg = d3.select('svg');

    svg.append('g')
        .attr('class','x axis')
        .attr('transform', 'translate(0,670)')
        .call(xAxis);

    svg.append('text')
        .text('Goals Against')
        .attr('class', 'label')
        .attr('transform','translate(200,705)');

    svg.append('g')
        .attr('class','y axis')
        .attr('transform', 'translate(50)')
        .call(yAxis);

    svg.append('text')
        .text('Goals For')
        .attr('class', 'label')
        .attr('transform','translate(10,360) rotate(90)');

    datum.forEach(function(country){
        createCountryBubble(svg, xScale(+country['Goals_Against']), yScale(+country['Goals_For']),
                            rScale(+country['Played']), country);

    });
});


function createCountryBubble(svg, goalsAgainst, goalsFor, gamesPlayed, country) {
    // svg - a d3-selection of the svg element - think of this a pointer to the svg DOM

    // goalsAgainst - a scaled pixel value in the plot's x-coordinate system for this country's "Goals_Against" property

    // goalsFor - a scaled pixel value in the plot's y-coordinate system for this country's "Goals_For" property

    // gamesPlayed - a scaled pixel value in a square root range for the radius of the circle, corresponds to this
               // country's "Played" property which is the total matches played in the World Cup

    // country - the country object, has key-value pairs from the data table (e.g. country, Wins, Losses, Best_Finish)


    // **** Your JavaScript code goes here ****

    // Activity 1.1: Append a circle for each country (make sure to assign it to a variable)
    var countryCircle = svg.append('circle');
    console.log(countryCircle);



    //Activity 1.2: Set the circle's radius on the scaled gamesPlayed value
    countryCircle.attr('r', gamesPlayed);

    //Activity 1.3: Set the center position based on the scaled (goalsAgainst, goalsFor) values
    //I need to call this twice as it takes one argument
    countryCircle.attr('cx', goalsAgainst);
    countryCircle.attr('cy', goalsFor);

    //Challenge #1: Set the circle's color based on the colorMap for Best_finish for this country
    var colorMap = {
        "Winner": "#8C0200",
    	"Runner-up": "#C00F0D",
    	"Third Place": "#FF1F3A",
    	"Fourth Place": "#F2898F",
    	"Quarter-finals": "#DDDDDD",
    	"First Round": "#DDDDDD",
    	"Second Round": "#DDDDDD"
    }
    var country_place = country.Best_finish;
    if (country_place == "Winner") {
        countryCircle.style('fill', colorMap["Winner"]);
    } else if (country_place == "Runner-up") {
        countryCircle.style('fill', colorMap["Runner-up"]);
    } else if (country_place == "Third Place") {
        countryCircle.style('fill', colorMap["Third Place"]);
    } else if (country_place == "Fourth Place") {
        countryCircle.style('fill', colorMap["Fourth Place"]);
    } else if (country_place == "Quarter-finals") {
        countryCircle.style('fill', colorMap["Quarter-finals"]);
    } else if (country_place == "First Round") {
        countryCircle.style('fill', colorMap["First Round"]);
    } else {
        countryCircle.style('fill', colorMap["Second Round"]);
    }

    //Challenge #2: If United States, Germany or Brazil add a label below the bubble

    var country_name = country["Country"];
    if (country_name == "United States" || country_name == "Germany" || country_name == "Brazil") {
        var text_element = svg.append("text");
        text_element.attr('x', goalsAgainst - 20);
        text_element.attr('y', goalsFor + gamesPlayed + 10);
        text_element.text(country_name);
    }
    /* x == 226 and y == 550 and US label is shown correctly.*/
    /*if (country_name == "United States") {
	var text_element = svg.append("text");
	text_element.attr('x', goalsAgainst - 20);
	text_element.attr('y', goalsFor + gamesPlayed + 50);
	text_element.text(country_name);
     }

    if (country_name == "Germany" || country_name == "Brazil") {
        var text_element = svg.append("text");
        text_element.attr('x', goalsAgainst - 26);
        text_element.attr('y', goalsFor + gamesPlayed + 11);
        text_element.text(country_name);
    }*/
}
