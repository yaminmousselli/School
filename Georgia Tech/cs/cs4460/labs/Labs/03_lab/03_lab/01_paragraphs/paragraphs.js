// **** Your JavaScript code goes here ****
    //var dataset;
    var csvPath = "baseball_hr_leaders_2017.csv";
    d3.csv(csvPath, function(error, data) {
        if (error) {
            console.log(error);
        } else {
            console.log(data);
        }
        //dataset = data;
        d3.select('body').selectAll('p')
            .data(data)
            .enter()
            .append('p')
            .text(function(d) {
                return d['rank']+ ". " + d['name'] + " with" + " " +  d['homeruns'] + " homeruns";
            });
//challenge 1
var my_table =  d3.select('body').selectAll('#homerun-table')
                .append('table')
                .append('thead');

        // Lines 23-37 are unecessary because I already have // the html code.

        // var columns = my_table.append('tr');
        //
        // var rank_header = columns.append('td')
        //                         .text('Rank');
        // var player_header = columns.append('td')
        //                         .text('Player');
        // var homerun_header = columns.append('td')
        //                         .text('Home Runs');
        //
        //
        // var table_body = d3.select('table').append('tbody');
        // var tuple = d3.select('tbody').selectAll('tr')
        //                 .data(data)
        //                 .enter()
        //                 .append('tr');

                    tuple.append('td')
                        .text(function(d, i) {
                            return d['rank'];
                        });
                    tuple.append('td')
                        .text(function(d,i) {
                            return d['player'];
                        });
                    tuple.append('td')
                        .text(function(d,i) {
                            return d['homeruns']
                        });
    });
    /*

    For the first question, it's because you already have a <p> element in the html (the one you put your name in) before you call the d3 methods, so basically this is what happens:
// var columns = my_table.append('tr');
        //
        // var rank_header = columns.append('td')
        //              // var columns = my_table.append('tr');
        //
        // var rank_header = columns.append('td')
        //                         .text('Rank');
        // var player_header = columns.append('td')
        //                         .text('Player');
        // var homerun_header = columns.append('td')
        //                         .text('Home Runs');
        //
        //
        // var table_body = d3.select('table').append('tbody');
        // var tuple = d3.select('tbody').selectAll('tr')
        //                 .data(data)
        //                 .enter()
        //                 .append('tr');           .text('Rank');
        // var player_header = columns.append('td')
        //                         .text('Player');
        // var homerun_header = columns.append('td')
        //                         .text('Home Runs');
        //
        //
        // var table_body = d3.select('table').append('tbody');
        // var tuple = d3.select('tbody').selectAll('tr')
        //                 .data(data)
        //                 .enter()
        //                 .append('tr');
1. When you call d3.select('body').selectAll('p'), that existing <p> element is included in the selection

2. The data('dataset') method then binds the first data entry (the one with Stanton and 57 homeruns) to that existing <p> element

3. Since we have 11 data entries, d3 needs to add 10 new elements. The enter() method creates the 10 new placeholder elements to be added

4. The append('p') then creates 10 actual new <p> elements in the html document, and returns the selection of those 10 elements to you.

5. When you call text() on the selection, it only applies to the 10 elements which corresponds to data point 2-11, but not the first one.
*/
