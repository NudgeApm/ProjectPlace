<!DOCTYPE html>
<html  xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <meta http-equiv="content-type" content="text/html;  charset=utf-8"/>
    <title>
       Google Image Events Sample
    </title>
    <script type="text/javascript" src="https://www.google.com/jsapi"></script>
    <script type="text/javascript">
      google.load('visualization', '1.0', {packages: ['imagechart']});
    </script>
    <script type="text/javascript">
      function drawVisualization() {
        var dataTable = new google.visualization.DataTable();
        dataTable.addColumn('number');
        //dataTable.addColumn('number');
        dataTable.addRows([
          [50],
          [60],
          [100],
          [40],
          [20]
        ]);    

        var options = {cht:  'bvs', chs: '300x125',  colors:['#4D89F9','#C6D9FD'],
                       chds:'0,160', enableEvents:true};

        var chart = new  google.visualization.ImageChart(document.getElementById('visualization'));
        chart.draw(dataTable, options);

        // Assign  event  handler
        google.visualization.events.addListener(chart, 'onclick', mouseEventHandler);
      }

      google.setOnLoadCallback(drawVisualization);

      // Define an event handler
      function mouseEventHandler(event)  {
        document.getElementById('debugger').innerHTML += "You clicked " + event.region + "<br/>";
      }

    </script>
  </head>
  <body style="font-family: Arial;border: 0 none;">
    <div id="visualization" style="width: 300px; height: 300px;"></div>
    <div id="debugger"></div>
  </body>
  <table>
  <tr>
  <td>Number of industries:</td>
  <td></td>
  </tr></table>
</html>