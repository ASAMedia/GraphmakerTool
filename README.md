# GraphmakerTool
The GraphmakerTool is made for creating and manipulating graphs.
<div>
  <img src="/images/mainwindow1.PNG" alt="Screenshot" title="Screenshot-1" width="50%" style="margin:5px"/>
</div>
<br>
<hr>
Features:
<ul>
  <li>Creating Graph</li>
  <li>Adding nodes/edges</li>
  <li>Deleting nodes/edges</li>
  <li>Move nodes to customize graph</li>
  <li>Add additional information to nodes</li>
  <li>Viewmode/Editmode</li>
  <li>3 default graphs with different drawing options</li>
</ul>
<br>
The graph class contains all vertices and edges in separate dynamic lists. The vertices extend the circle and the edges the line class from javafx.
This tactic preserves a fast and simple graphical output to the window pane.
Edges and vertices which are bind together can be found by comparing the vertex location and the start/end location of the edge.
Each vertex has a hashmap which contains additional information.
All additional data such as name, connections, number and attributes must be added when adding a vertex to the graph.  
<hr>
<h3>Add node</h3>
<div>
  <img src="/images/addwindow.PNG" alt="Screenshot" title="Screenshot-2" width="50%" style="margin:5px"/>
  <p>By clicking the add button on the main window a popup window opens.<br> 
  Through the given input fields a customized node can be added.<p><br>
</div>
  <h4>Note:</h4> The following input layout has to be used, else errors could occur!<br>
  <ul>
  <li>Number: int</li>
  <li>Name: String</li>
  <li>Attributes: String:String,String:String, ...</li>
  <li>Connections: int,int,...</li>
</ul>
<br><hr>
<h3>Delete node</h3>
<img src="/images/deleteanimation.gif" alt="Screenshot" title="Screenshot-3" width="50%" style="margin:5px"/>
To delete a node right click on it an select delete on the popup menu(View mode needs to be selected).
<br><hr>
<h3>Add/Delete Edge</h3>
 <table style="width:100%">
  <tr>
    <th><img src="/images/addedgeanimation.gif" alt="Screenshot" title="Screenshot-4" width="90%" style="margin:5px"/></th>
    <th><img src="/images/deleteedgeanimation.gif" alt="Screenshot" title="Screenshot-5" width="90%" style="margin:5px"/></th>
  </tr>
  <tr>
    <td>To add a edge switch to edit mode and left click on the two vertices you wish to connect.</td>
    <td>To delete a edge switch to edit mode and right click on the two vertices you wish to connect.</td>
  </tr>
</table> 
<br><hr>
<h3>UML Diagram</h3>
<img src="/images/uml.PNG" alt="Screenshot" title="Screenshot-6" width="50%" style="margin:5px"/>
<hr>

<i>
Copyright (&copy;) 2019 ASAMedia<br>
Approval for reuse, processing and distribution will not be granted.<br>
If you have any questions, please contact the author.</i>
