(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-3ae46f56"],{"07a5":function(t,e,a){},9406:function(t,e,a){"use strict";a.r(e);var i=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{attrs:{id:"index"}},[a("el-container",[a("el-aside",{attrs:{width:"200px"}},[a("el-row",{staticClass:"index-num"},[a("el-col",{attrs:{span:22}},[a("div",{staticClass:"index-num-one"},[a("div",[t._v("今日浏览用户："),a("span",[t._v("320")]),t._v("人")]),t._v(" "),a("div",[t._v("今日注册用户："),a("span",[t._v("28")]),t._v("人")]),t._v(" "),a("div",[t._v("总注册用户："),a("span",[t._v(t._s(t.total.user))]),t._v("人")])])]),t._v(" "),a("el-col",{attrs:{span:22}},[a("div",{staticClass:"index-num-two"},[a("div",[t._v("今日发布作家："),a("span",[t._v("320")]),t._v("人")]),t._v(" "),a("div",[t._v("今日注册作家："),a("span",[t._v("28")]),t._v("人")]),t._v(" "),a("div",[t._v("总发布作家："),a("span",[t._v(t._s(t.total.article))]),t._v("人")])])]),t._v(" "),a("el-col",{attrs:{span:22}},[a("div",{staticClass:"index-num-three"},[a("div",[t._v("作品类别："),a("span",[t._v(t._s(t.total.category))]),t._v("种")]),t._v(" "),a("div",[t._v("平均发布量："),a("span",[t._v("28")]),t._v("种")]),t._v(" "),a("div",[t._v("待发布量："),a("span",[t._v("12")]),t._v("种")])])]),t._v(" "),a("el-col",{attrs:{span:22}},[a("div",{staticClass:"index-num-four"},[a("div",[t._v("今日用户评论："),a("span",[t._v("320")]),t._v("条")]),t._v(" "),a("div",[t._v("今日作家回复："),a("span",[t._v("28")]),t._v("条")]),t._v(" "),a("div",[t._v("今日总评论："),a("span",[t._v("466")]),t._v("条")])])])],1)],1),t._v(" "),a("el-main",[a("div",{staticClass:"index-curve"},[a("el-row",[a("el-col",{attrs:{span:18}},[a("div",{staticClass:"index-curve-chart"},[a("div",{staticStyle:{"min-width":"400px",height:"400px"},attrs:{id:"indexOrder"}})])]),t._v(" "),a("el-col",{attrs:{span:6}},[a("div",{staticClass:"index-curve-chart"},[a("div",{staticStyle:{"min-width":"200px",height:"250px"},attrs:{id:"indexBusiness"}})]),t._v(" "),a("div",{staticClass:"index-curve-chart"},[a("div",{staticStyle:{"min-width":"200px",height:"150px"},attrs:{id:"indexBrush"}})])])],1)],1)])],1)],1)},n=[],s=a("aaa5"),o=a.n(s),l=a("dac6"),r=(a("fed1"),{name:"Index",data:function(){return{total:{}}},mounted:function(){this.toDraw(),this.toDrawBusiness(),this.toDrawBrush()},methods:{toDraw:function(){o.a.chart("indexOrder",{credits:{enabled:!1},colors:["#27A9E3","#28B779"],chart:{type:"column",spacing:[0,0,0,0]},title:{text:"待发布和已发布",y:20,style:{fontSize:"14px"}},xAxis:{categories:["3月13日","3月14日","3月15日","3月16日","3月17日","3月18日","3月19日"]},yAxis:{min:0,title:{text:""},stackLabels:{enabled:!0,style:{fontWeight:"bold",color:o.a.theme&&o.a.theme.textColor||"gray"}}},legend:{align:"right",x:-30,verticalAlign:"top",y:25,floating:!0,backgroundColor:o.a.theme&&o.a.theme.background2||"white",borderColor:"#CCC",borderWidth:1,shadow:!1},tooltip:{pointFormat:'<span style="color:{series.color}">{series.name}</span>: <b>{point.y}篇</b><br/>',shared:!0},plotOptions:{column:{stacking:"normal",dataLabels:{enabled:!0,color:o.a.theme&&o.a.theme.dataLabelsColor||"white",style:{textOutline:"1px 1px black"}}}},series:[{name:"待发布",data:[234,545,373,454,363,243,101]},{name:"已发布",data:[234,545,373,454,363,243,101]}]})},toDrawBusiness:function(){o.a.chart("indexBusiness",{colors:["#62BA15","gray"],credits:{enabled:!1},chart:{margin:[0,0,0,0]},title:{text:"发布人数",y:20,style:{fontSize:"14px"}},xAxis:{visible:!1,title:{text:"就业人数"}},yAxis:{visible:!1,title:{text:"就业人数"}},legend:{enabled:!1,layout:"vertical",align:"right",verticalAlign:"middle"},plotOptions:{series:{label:{connectorAllowed:!1},pointStart:2010}},series:[{name:" ",data:[43934,52503,57177,69658,97031,119931,137133,154175]},{name:" ",data:[11744,17722,16005,19771,20185,24377,32147,39387]}],responsive:{rules:[{condition:{maxWidth:500},chartOptions:{legend:{layout:"horizontal",align:"center",verticalAlign:"bottom"}}}]}})},toDrawBrush:function(){o.a.chart("indexBrush",{colors:["#0A7BCB","#0899FD","#4CB8FE","#8ED1FE"],credits:{enabled:!1},chart:{type:"pie",spacing:[0,0,0,0],margin:[0,0,0,-70]},title:{floating:!0,x:-35,text:"发布人总计<br/>999009",style:{color:"#333333",fontSize:"10px"}},tooltip:{pointFormat:"{series.name}占比:<b>{point.y}%</b>"},legend:{align:"right",verticalAlign:"middle",itemStyle:{color:"#333333",cursor:"pointer",fontSize:"12px",weight:"100px"},layout:"vertical"},plotOptions:{pie:{innerSize:100,depth:25,dataLabels:{enabled:!0,distance:-15,format:"<b>{point.y}%</b>",style:{color:"white",fontSize:"12px"}},showInLegend:!0,allowPointSelect:!0,cursor:"pointer"}},series:[{type:"pie",innerSize:"50%",name:"作者数量",data:[["高级用户",40],["普通用户",50],["新手上路",10]]}]},(function(t){var e=t.series[0].center[1],a=parseInt(t.title.styles.fontSize);t.setTitle({y:e+a/2-6})}))},loadArticle:function(){var t=this;l["a"].get("/manager/statistics/getCount").then((function(e){t.total=e.data.data}))}},created:function(){this.loadArticle()}}),d=r,c=(a("f8a7"),a("a779"),a("5511")),v=Object(c["a"])(d,i,n,!1,null,"7abf8eae",null);e["default"]=v.exports},a779:function(t,e,a){"use strict";var i=a("d272"),n=a.n(i);n.a},d272:function(t,e,a){},f8a7:function(t,e,a){"use strict";var i=a("07a5"),n=a.n(i);n.a}}]);