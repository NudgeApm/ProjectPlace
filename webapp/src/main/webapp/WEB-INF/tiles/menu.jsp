<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <table>
  <tr>
    <td>
      <div class="container">
          <div class="nav-collapse"> 
        <ul class="nav">
          <a class="btn btn-primary" href="selectStar.htm">S. star</a>
          <a class="btn btn-primary" href="selectOneColumn.htm">S. 1 colonne (nom)</a>
          <a class="btn btn-primary" href="selectOneColumnLoop.htm">S. 1 colonne (nom) *100</a>
          <a class="btn btn-primary" href="selectOneColumnRecursif.htm">S. 1 colonne (nom) recursif</a>
          <a class="btn btn-primary" href="insertData.htm">Insert Data</a> 
          <a class="btn btn-primary" href="runThread.htm">Lancer thread</a>
          <a class="btn btn-primary" href="selectProduitCartesien.htm">S. produit cartesien</a>
          <a class="btn btn-primary" href="selectStar100Items.htm">S. star 100 items</a>  
          <a class="btn btn-primary" href="selectStar1000Items.htm">S. star 1000 items</a>    
          <a class="btn btn-primary" href="selectStar10000Items.htm">S. star 10000 items</a>
          <a class="btn btn-primary" href="viewDoesntExist.htm">No view there</a>
          <a class="btn btn-primary" href="viewNullPointerException.htm">Null Pointer Exception</a>
          <a class="btn btn-primary" href="getTableSize.htm"> Table size</a>
          <a class="btn btn-primary" href="insertDataBatch.htm">Insert Data Batch</a>          
          <a class="btn btn-primary" href="deleteTables.htm">Delete tables</a>                        
          <a class="btn btn-primary" href="lowTransactionAccoringlyToVolumeOfData.htm">Slow transaction depending on contrat count</a>          
                   
        </ul>
      </div>
    </div>
    </td>

  </tr>
</table>

${breadcrumbs}
