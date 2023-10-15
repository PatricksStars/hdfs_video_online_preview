<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>Videos</title>
    <script type="text/javascript" src="js/jquery-3.5.1.js"></script>
</head>
<body>

  <#list videolist as videos>
    <div style="width:360px;height:300px;">
      <a href="/video?videoname=${videos.code}">
        <img src="/videoimage?videoname=${videos.code}" style="width:360px;height:260px;">
        </img>
      </a>
      
      <br/> <p>${videos.name}</p>
    </div>
    
    <br/><br/><br/>
  </#list>
  <br/><br/><br/>
  
  <strong>
    <div style="font-size:53px" id = "pageBar">
    </div>
  </strong>
</body>

<script type="text/javascript">
  var pageBarDiv = $("#pageBar")[0];

  var end = parseInt("${count}");
  
  var pageBarHtml = "<table>";
  for(var index = 1; index < end; index++){
    if(index%6 == 1){
      pageBarHtml +="<tr>"
    }
    pageBarHtml += "<td style='width:168px'><a href='/view?index=" + index + "'>" + index + "</a></td>";
    if(index%6 == 0 || index == end-1){
      pageBarHtml +="</tr>"
    }
    
  }
  pageBarHtml += "</table>";
  pageBarDiv.innerHTML = pageBarHtml;

</script>

</html>