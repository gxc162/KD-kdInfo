<!--首页-->
<template>
  <div id="index">
    <el-container>
      <el-aside width="200px">
        <el-row class="index-num">
          <el-col :span="22">
            <div class="index-num-one">
              <div>今日浏览用户：<span>320</span>人</div>
              <div>今日注册用户：<span>28</span>人</div>
              <div>总注册用户：<span>{{total.user}}</span>人</div>
            </div>
          </el-col>
          <el-col :span="22">
            <div class="index-num-two">
              <div>今日发布作家：<span>320</span>人</div>
              <div>今日注册作家：<span>28</span>人</div>
              <div>总发布作家：<span>{{total.article}}</span>人</div>
            </div>
          </el-col>
          <el-col :span="22">
            <div class="index-num-three">
              <div>作品类别：<span>{{total.category}}</span>种</div>
              <div>平均发布量：<span>28</span>种</div>
              <div>待发布量：<span>12</span>种</div>
            </div>
          </el-col>
          <el-col :span="22">
            <div class="index-num-four">
              <div>今日用户评论：<span>320</span>条</div>
              <div>今日作家回复：<span>28</span>条</div>
              <div>今日总评论：<span>466</span>条</div>
            </div>
          </el-col>
        </el-row>
      </el-aside>
      <el-main>
        <div class="index-curve">
          <el-row>
            <el-col :span="18">
              <div class="index-curve-chart">
                <div id="indexOrder" style="min-width:400px;height:520px"></div>
              </div>
            </el-col>
            <el-col :span="6">
              <div class="index-curve-chart">
                <div id="indexBusiness" style="min-width:200px;height:250px"></div>
              </div>
              <div class="index-curve-chart">
                <div id="indexBrush" style="min-width:200px;height:150px"></div>
              </div>
            </el-col>
          </el-row>
        </div>
      </el-main>
    </el-container>
  </div>
</template>
<script>
import Highcharts from 'highcharts'
import axios from '@/http/axios'
import qs from 'qs'
export default {
  name: 'Index',
  data(){
    return{
      total:{}
    }
  },
  mounted() {
    this.toDraw();
    this.toDrawBusiness();
    this.toDrawBrush();
  },
  methods:{
    //绘制堆叠柱状图
    toDraw(){
      var chart = Highcharts.chart('indexOrder', {
        credits:{
          enabled:false
        },
        colors:['#27A9E3','#28B779'],
        chart: {
          type: 'column',
          spacing : [0, 0 , 0, 0],
        },
        title: {
          text: '文章发布情况',
          y:20,
          style:{ 
            "fontSize": "14px" 
          }
        },
        xAxis: {
          categories: ['3月13日','3月14日','3月15日','3月16日','3月17日','3月18日','3月19日']
        },
        yAxis: {
          min: 0,
          title: {
            text: ''
          },
          stackLabels: {  // 堆叠数据标签
            enabled: true,
            style: {
              fontWeight: 'bold',
              color: (Highcharts.theme && Highcharts.theme.textColor) || 'gray'
            }
          }
        },
        legend: {
          align: 'right',
          x: -30,
          verticalAlign: 'top',
          y: 25,
          floating: true,
          backgroundColor: (Highcharts.theme && Highcharts.theme.background2) || 'white',
          borderColor: '#CCC',
          borderWidth: 1,
          shadow: false
        },
        tooltip: {
          pointFormat: '<span style="color:{series.color}">{series.name}</span>: <b>{point.y}篇</b>' +
          '<br/>',
          shared: true 
        },
        plotOptions: {
          column: {
            stacking: 'normal',
            dataLabels: {
              enabled: true,
              color: (Highcharts.theme && Highcharts.theme.dataLabelsColor) || 'white',
              style: {
                // 如果不需要数据标签阴影，可以将 textOutline 设置为 'none'
                textOutline: '1px 1px black'
              }
            }
          }
        },
        series: [{
          name: '待发布',
          data: [234,545,373,454,363,243,101]
        }, {
          name: '已发布',
          data: [234,545,373,454,363,243,101]
        }]
      });
    },
    /* 绘制商家饼状图 */
    toDrawBusiness(){
      var chart = Highcharts.chart('indexBusiness', {
        colors: ['#62BA15','gray'],
        credits:{
          enabled:false
        },
        chart:{
          margin:[0,0,0,0]
        }, 
        title: {
          text: '发布人数',
          y:20,
          style:{ 
            "fontSize": "14px" 
          }
        },
        xAxis: {
          visible:false,
          title: {
            text: '就业人数'
          }
        },
        yAxis: {
          visible:false,
          title: {
            text: '就业人数'
          }
        },
        legend: {
          enabled:false,
          layout: 'vertical',
          align: 'right',
          verticalAlign: 'middle'
        },
        plotOptions: {
          series: {
            label: {
              connectorAllowed: false
            },
            pointStart: 2010
          }
        },
        series: [{
          name: ' ',
          data: [43934, 52503, 57177, 69658, 97031, 119931, 137133, 154175]
        }, {
          name: ' ',
          data: [11744, 17722, 16005, 19771, 20185, 24377, 32147, 39387]
        }],
        responsive: {
          rules: [{
            condition: {
              maxWidth: 500
            },
            chartOptions: {
              legend: {
                layout: 'horizontal',
                align: 'center',
                verticalAlign: 'bottom'
              }
            }
          }]
        }
        });
    },
    /* 绘制买手饼状图 */
    toDrawBrush(){
      let chart = Highcharts.chart('indexBrush', {
        colors: ['#0A7BCB','#0899FD','#4CB8FE','#8ED1FE'],
        credits:{
          enabled:false
        },
        chart: {
          type:'pie',
          spacing : [0, 0 , 0, 0],
          margin: [0, 0, 0, -70]
        },
        title: {
          floating:true,
          x:-35,  
          text: '发布人总计<br/>999009',
          style:{ "color": "#333333", "fontSize": "10px" }
        },
        tooltip: {
          pointFormat: '{series.name}占比:<b>{point.y}%</b>'
        },
        legend: {
          align: 'right',
          verticalAlign: 'middle',
          /* floating: true, */
          itemStyle:{ 
            color: "#333333", 
            cursor: "pointer", 
            fontSize: "12px", 
            weight: "100px" 
          },
          layout:'vertical'
        },
        plotOptions: {
          pie: {
            innerSize: 100,
            depth: 25,
            dataLabels: {
              enabled: true,
              distance: -15,
              format:'<b>{point.y}%</b>',
              style: {
                color: 'white',
                fontSize:'12px'
              }
            },
            showInLegend: true,
            allowPointSelect: true,
            cursor: 'pointer'
          }
        },
        series: [{
          type: 'pie',
          innerSize: '50%',
          name: '作者数量',
          /* 百分比 */
          data: [
            ['高级用户',40],
            ['普通用户',50],
            ['新手上路',10]
          ]
        }]
      }, function(c) {
          // 环形图圆心
          let centerY = c.series[0].center[1],
            titleHeight = parseInt(c.title.styles.fontSize);
          // 动态设置标题位置
          c.setTitle({
            y:centerY + titleHeight/2-6
          });
      });
    },
    loadArticle(){
      axios.get('/manager/statistics/getCount').then((res)=>{
        this.total = res.data.data
      });
    },
  },
  created(){
    this.loadArticle()
  }
}
</script>
<style>
  /* 设置表格的头部背景 */
  #index .el-table__header thead  tr th{
    background:none;
  }
</style>
<style lang="scss" scoped>
  #index{
    font-size:14px;
    /* 设置外边距 */
    .index-num{
      margin:0 0 15px 0;
      float:right;
      padding: 0 0 0 15px;
      /* 设置4块div统一的样式 */
      .index-num-one,.index-num-two,.index-num-three,.index-num-four{
        color: white;
        background:rgb(122, 205, 243);
        border-radius: 5px;
        padding:10px 10px;
        margin:50px 5px 15px 0;
      }
      .index-num-two{
        background:rgb(48, 216, 143);
      }
      .index-num-three{
        background:rgb(253, 198, 114);
      }
      .index-num-four{
        background:rgb(34, 116, 240);
      }
      .index-curve-option{
        margin-bottom:5px;
      }
    }
   
  }
  #indexBrush {
    margin-top: 70px;
  }
</style>
