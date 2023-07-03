<!--
 * @Author: your name
 * @Date: 2022-03-03 10:33:08
 * @LastEditTime: 2022-07-02 14:38:05
 * @LastEditors: dingyinglit 884160676@qq.com
 * @Description: 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
 * @FilePath: \sfcc\src\views\dataScreen\index.vue
-->
<template>
  <div class="screen-container">
    <div class="topArea">
      <img src="../../assets/images/title.png"/>
    </div>
    <el-row class="containerBox">
      <el-col :span="5" style="position:relative">
        <router-link to="/" class="jumpButton"> < 返回首页</router-link>
        <div class="grid-content bg-purple leftContainer">
          <div v-for="(item,index) in leftData" class="leftDataItem unifiedBorder" @click="changeChart(index)">
            <div>
              <p>{{item.title}}</p>
              <p>{{item.num}}<span>{{item.unit}}</span></p>
            </div>
            <img :src="item.icon" />
            <div class="unifiedBorderBox"></div>
           </div>
        </div>
      </el-col>
      <el-col :span="13">
        <div class="grid-content bg-purple centerContainer">
          <div class="centerDataBox unifiedBorder">
            <div v-for="item in centerData" class="centerDataItem">
              <img :src="require(`../../assets/images/${item.icon}.png`)" alt="">
              <p>{{item.title}}</p>
              <p>{{item.num}}</p>
            </div>
            <div class="unifiedBorderBox"></div>
          </div>
          <div class="centerMapBox">
            <div ref="mapChart" class="map"></div>
          </div>
        </div>
      </el-col>
      <el-col :span="6">
        <div class="grid-content bg-purple rightContainer">
          <div class="evaluateBox unifiedBorder">
            <div class="unifiedTitle">好评统计</div>
            <div ref="myPieChart" class="pieChart" ></div>
            <div class="unifiedBorderBox" style="height:1px"></div>
          </div>
          <div class="echartsBox unifiedBorder">
            <div class="unifiedTitle">
              <span :class="{'activeText':echartsName == '工单统计'}" @click="changeChart(0)">工单统计</span>
              <span :class="{'activeText':echartsName == '商机'}" @click="changeChart(1)">商机</span>
              <span :class="{'activeText':echartsName == '评价'}" @click="changeChart(2)">评价</span>
            </div>
            <div ref="myChart" class="lineChart" ></div>
            <div class="unifiedBorderBox" style="height:1px"></div>
          </div>
        </div>
      </el-col>
    </el-row>
  </div>
</template>
<script>
import echarts from 'echarts';
import Vue from 'vue';
import request from '@/utils/request';
import { getIndexData,getScore } from '@/api/index.js';
import { searchArchive } from '@/api/archives.js';
import { getOrderNum } from '@/api/workorder.js';
import { formatDate } from '@/utils/date';
import HLJ from "@/assets/js/HLJ.json"
Vue.prototype.$echarts = echarts
export default {
  data() {
    return {
      leftData:[
        {
          id:1,
          title:"工单数量",
          num:"0",
          unit:"个",
          icon:require("@/assets/images/gongdan.png")
        },
        {
          id:2,
          title:"商机",
          num:"0",
          unit:"个",
          icon:require("@/assets/images/shangji.png")
        },
        {
          id:3,
          title:"评价",
          num:"0",
          unit:"个",
          icon:require("@/assets/images/pingjia.png")
        },
        {
          id:4,
          title:"档案",
          num:"0",
          unit:"个",
          icon:require("@/assets/images/dangan.png")
        }
      ],
      centerData:[
        {
          title:"热线会话总量",
          num:"0",
          icon:"rexian"
        },
        {
          title:"用户总量",
          num:"0",
          icon:"user"
        },{
          title:"部门数",
          num:"0",
          icon:"dept"
        },{
          title:"知识库",
          num:"0",
          icon:"knowledge"
        },{
          title:"话务员总量",
          num:"0",
          icon:"servicers"
        }
        // ,{
        //   title:"在线话务员",
        //   num:"0",
        //   icon:"online"
        // }
      ],
      msg_host:msg_host,
      http_urla:"http://" + msg_host + ":" + http_url,
      echartsXData:[],
      echartsData:[],
      queryArchive:{
        label:"商机",
        startTime:"",
        endTime:formatDate(new Date(), 'yyyy-MM-dd hh:mm:ss')
      },
      dataSection:7,
      echartsName:"工单统计",
      scoreData:{},
      pieChart:null,
      myChart:null,
      myMapChart:null,
    }
  },
  mounted() {
    // 初始化评价扇形图图表
    this.pieChart = this.$echarts.init(this.$refs.myPieChart)
    // 初始化折线图echarts实例
    this.myChart = this.$echarts.init(this.$refs.myChart)
    // 初始化地图实例图表
    this.myMapChart = this.$echarts.init(this.$refs.mapChart)
    // 获取展示数据
    this.getAllData();
    // 获取工单折线图数据
    this.getWorkOrderCharts();
    // 获取评价扇形图数据
    this.getScoreData();
    // 初始化地图图表
    if(this.$refs.mapChart){
      this.initMapChart()
    }
    let this_ = this
    window.onresize = () => {
      this_.pieChart.resize();
      this_.myChart.resize();
      this_.myMapChart.resize();
    }
  },
  methods: {
    // 获取统计数据
    getAllData(){
      getIndexData({label:"商机"}).then((res)=>{
        // 工单数
        this.leftData[0].num = res.data.workOrderCount || 0;
        // 商机数
        this.leftData[1].num = res.data.totalCount || 0;
        // 评价数
        this.leftData[2].num = res.data.scoreCount || 0;
        // 档案数
        this.leftData[3].num = res.data.archivesCount || 0;
        // 用户数
        this.centerData[1].num = res.data.memberCount || 0;
        // 部门数
        this.centerData[2].num = res.data.deptCount || 0;
        // 知识库数
        this.centerData[3].num = res.data.knowledgeCount || 0;
      })
      request({
        url:`${this.http_urla}query/queryCdrs`,
        method:"post",
        data: {
          tenantId:targetTenantId.toString(),
          actionID:exten,
          currentPage: 1,
          pageSize: 10,
          queue:queue[0]
        }
      }).then(response => {
        // 查询所有会话总量
        this.centerData[0].num = response.totalCount || 0;
      })
      let _this = this;
      setTimeout(function () {
        request({
          url:`${_this.http_urla}query/queryQueue`,
          method:"post",
          data: {
            tenantId:targetTenantId.toString(),
            actionID:exten,
            queue:queue[0]
          }
        }).then(response => {
          // 查询座席总量
          if(response.data.agent) _this.centerData[4].num = response.data.agent.length;
        })
      },1200)
    },
    // 获取评分数据
    getScoreData(){
      console.log(1)
      getScore().then((res)=>{
      console.log(2)
        this.scoreData = res.data;
        this.drawPie()
      })
    },
    // 切换左侧数据 展示右侧图表
    changeChart(index){
      switch(index){
        case 0 :
          this.echartsName = '工单统计'
          this.getWorkOrderCharts();
        break;
        case 1 :
          this.echartsName = '商机';
          this.getArchivesCharts();
        break;
        case 2 :
          this.echartsName = '评价';
          this.getScoreCharts()
        break;
        default :
        break;
      }
    },
    // 获取今天之前的日期
    fun_date(aa,type){  
      var date1 = new Date(),  
      time1=date1.getFullYear()+"-"+(date1.getMonth()+1)+"-"+date1.getDate();//time1表示当前时间  
      var date2 = new Date(date1);  
      date2.setDate(date1.getDate()+aa);  
      let month = date2.getMonth()+1 > 9 ? date2.getMonth()+1 : '0'+(date2.getMonth()+1);
      let date = date2.getDate() > 9 ? date2.getDate() : '0'+date2.getDate();
      var time2 = (type == 'month' ? '' : date2.getFullYear()+"-")+month+"-"+date;  
      return time2;
    },
    // 获取档案echarts数据
    getArchivesCharts(){
      this.echartsXData = [];
      this.echartsData = [];
      let num = -(parseFloat(this.dataSection)-1); // 定义查询多少天的数据
      this.queryArchive['startTime'] = this.fun_date(num)+ ' 00:00:00'; // 定义开始时间
      for(let i=0;i<this.dataSection;i++){ // 处理x坐标轴
        this.echartsXData.unshift(this.fun_date(-i,'month'))
      }
      searchArchive(this.queryArchive).then((response)=>{
        this.echartsXData.map((item,index)=>{
          let datasource = response.data[`${new Date().getFullYear()}-${item}`];
          this.echartsData.push(datasource || 0)
        })
        this.drawLine();
      })
    },
    //获取评价折线图
    getScoreCharts(){
      this.echartsXData = [];
      this.echartsData = [];
      Object.keys(this.scoreData).map((res)=>{
        this.echartsXData.push(`${res}`);
        this.echartsData.push(this.scoreData[res]);
      })
      this.drawLine();
    },
    // 绘制饼图
    drawPie(){
      let data = [
        {
          value:(this.scoreData['3'] || 0)+(this.scoreData['4'] || 0)+(this.scoreData['5'] || 0),
          name:'好评'
        },
        {
          value:(this.scoreData['1'] || 0)+(this.scoreData['2'] || 0),
          name:'差评'
        }
      ];
      this.pieChart.setOption({
        legend:{
          type:"plain",
          right:0,
          top:5,
          textStyle:{
            color:"#fff",
            fontSize:12,
          },
          itemGap:5,
          icon:"circle",
          itemWidth:14,
        },
        color:['#5C6DB3','#A56899'],
        series: [
          {
            name: '评价',
            type: 'pie',
            radius: '65%',
            data: data,
            label: {
              formatter: '{value|{d}%}\n{name|{b}率}',
              rich: {
                name:{
                  fontSize: 12,
                  color: '#999'
                },
                value:{
                  fontSize: 12,
                  color: '#FFFFFF'
                }
              },
              lineHeight:15,
            },
            labelLine: {
              length: 10,
              length2: 20,
              lineStyle:{
                color:"#999999"
              }
            },
            emphasis: {
              itemStyle: {
                // shadowBlur: 10,
                // shadowOffsetX: 0,
                // shadowColor: 'rgba(0, 0, 0, 0.5)'
              }
            }
          }
        ]
      })
    },
    // 绘制表格
    drawLine(){
      // 绘制图表
      this.myChart.setOption({
        grid: {
          top: '15%',
          bottom:'13%',
          left:'10%',
          right:'2.5%'
        },
        tooltip: {
          formatter:'{a}<br/>{b0}'+(this.echartsName == '评价' ? "分" : "")+': {c0}个'
        },
        xAxis:{
          name:this.echartsName == '评价' ? "(分)" : "",
          type: 'category',
          // boundaryGap: false,
          data:this.echartsXData,
          axisLine:{
            show:false
          },
          axisTick:{
            show:false
          },
          axisLabel:{
            color:'#999999',
            fontSize:12,
          },
          nameTextStyle:{
            color:'#999999',
            fontSize:12,
            align:"right",
            padding:[0,10,-27,0]
          }
        },
        yAxis: {
          name:"(个)",
          axisLine:{
            show:false
          },
          axisTick:{
            show:false
          },
          splitLine:{
            show:true,
            lineStyle:{
              color:"#16336C",
              opacity:0.05
            }
          },
          axisLabel:{
            color:'#999999',
            fontSize:12
          },
          nameTextStyle:{
            color:'#999999',
            fontSize:12,
            padding: [0, 0, -20, 10]
          }
        },
        color:['#C88BE8'],
        series: [
          {
            name:this.echartsName,
            data:this.echartsData,
            type:"line",
            lineStyle:{
              opacity:0.6
            },
            smooth: true,
            areaStyle: {
              opacity:0.6
            }
          }
        ]
      });
    },
    // 获取工单echarts数据
    getWorkOrderCharts(){
      this.echartsXData = ['未处理','处理中','结单','作废','超时未处理']
      this.echartsData = []
      getOrderNum().then((response)=>{
        this.echartsXData.map((item,index)=>{
          let datasource = response.data.filter(m=>m.status == index);
          let data = datasource.length > 0 ? datasource[0] : {};
          this.echartsData.push(data.count || 0)
        })
        this.drawLine();
      })
    },
    // 初始化地图
    initMapChart(){
      let that = this
      this.myMapChart.on('click', function (params) {
      });
      echarts.registerMap('HLJ', HLJ);
      let option = {
        geo: {
          type: 'map',
          map: 'HLJ', //'jiangxi'
          roam: true,
          geoIndex: 1,
          zoom: 1.2,  //地图的比例
          label: {
            normal: {
              show: true,
              textStyle: {
                color: '#fff',  //字体颜色
                fontSize: '12px'
              }
            },
            emphasis: {
              textStyle: {
                color: '#fff'  //选中后的字体颜色
              }
            }
          },
          itemStyle: {
            normal: {
              areaColor: 'rgba(9,46,60,0.1)',
              borderColor: '#0096ff',
              // shadowColor: '#0096ff',
              // shadowBlur: 5
            },
            emphasis: {
              areaColor: 'rgba(54,116,152,0.2)',
            }
          },
          regions:[
            {
              name: '黑河市',
              itemStyle: {
                  areaColor: 'rgba(239,130,110,0.2)',
              }
            },{
              name: '大兴安岭地区',
              itemStyle: {
                  areaColor: 'rgba(89,190,114,0.2)',
              }
            },{
              name: '齐齐哈尔市',
              itemStyle: {
                  areaColor: 'rgba(94,89,181,0.2)',
              }
            },{
              name: '伊春市',
              itemStyle: {
                  areaColor: 'rgba(138,204,83,0.2)',
              }
            },{
              name: '绥化市',
              itemStyle: {
                  areaColor: 'rgba(255,204,0,0.1)',
              }
            },{
              name: '大庆市',
              itemStyle: {
                  areaColor: 'rgba(9,46,60,0.1)',
              }
            },{
              name: '鹤岗市',
              itemStyle: {
                  areaColor: 'rgba(238,109,124,0.2)',
              }
            },{
              name: '佳木斯市',
              itemStyle: {
                  areaColor: 'rgba(53,129,93,0.2)',
              }
            },{
              name: '双鸭山市',
              itemStyle: {
                  areaColor: 'rgba(237,99,129,0.2)',
              }
            },{
              name: '七台河市',
              itemStyle: {
                  areaColor: 'rgba(9,46,60,0.1)',
              }
            },
            {
              name: '鸡西市',
              itemStyle: {
                  areaColor: 'rgba(91,147,250,0.3)',
              }
            },
            {
              name: '哈尔滨市',
              itemStyle: {
                  areaColor: 'rgba(166,116,254,0.3)',
              }
            },
            {
              name: '牡丹江市',
              itemStyle: {
                  areaColor: 'rgba(240,146,101,0.3)',
              }
            }
          ]
        },
        series:[
          {
            name: '哈尔滨市商务局',
            type: 'scatter',
            coordinateSystem: 'geo',
            symbol: `image://${process.env.IMAGE_API}/uploads/businessVerify/2022-3-7/20220307162136_64573.png`,
            symbolSize: 30,
            data: [{
              city:"黑龙江省",
              name:"黑龙江省商务局",
              value:[126.669585,45.746445]
            }], 
            emphasis:{
              label:{
                show:true,
                position:"top",
                color:"#fff",
                fontSize:"16",
                formatter: '哈尔滨市商务局'
              }
            }
          }
        ]
      }
      this.myMapChart.setOption(option , true);
    }
  },
}
</script>
<style lang="scss" scoped>
  *{
    margin:0;
    padding:0;
  }
  .screen-container{
    background:#02121A;
    width:100%;
    min-height:100vh;
    padding-top:4.84vw;
    box-sizing:border-box;
    .topArea{
      width:100vw;
      height:4.84vw;
      background:url("../../assets/images/header.png") #02121A no-repeat;
      background-size:100% 100%;
      position:fixed;
      z-index:100;
      top:0;
      left:0;
      display:flex;
      align-items:center;
      justify-content:center;
      img{
        width:22vw;
      }
    }
    .jumpButton{
      font-size:14px;
      color:#fff;
      position:absolute;
      top:-25px;
      left:0;
    }
    .containerBox{
      padding:2vw;
      .leftContainer{
        padding-right:1vw;
        .leftDataItem{
          width:100%;
          padding:1.5vw;
          display:flex;
          align-items:center;
          justify-content:space-between;
          margin-bottom: 3vh;
          cursor:pointer;
          div:first-child{
            p:first-child{
              color:#28E3F2;
              font-size:1vw;
              margin-bottom:1vw;
            }
            p:last-child{
              color:#ED6280;
              font-weight:550;
              font-size:2vw;
              span{
                font-size:0.1vw;
                color:#fff;
                font-weight:normal;
                margin-left:0.1vw;
              }
            }
          }
          img{
            max-width:4vw;
            max-height:4vw;
          }
        }
      }
      .centerContainer{
        .centerDataBox{
          padding: 2vh 1vw 2.5vh;
          display:flex;
          align-items:center;
          justify-content:space-around;
          .centerDataItem{
            width: 8vw;
            text-align:center;
            img{
              width: 3.5vw;
              height: 3.5vw;
              margin-bottom: 1vh;
            }
            p{
              margin:0;
              margin-top: 0.6vh;
            }
            p:nth-child(2){
              font-size: 0.8vw;
              color: #28E3F2;
            }
            p:nth-child(3){
              font-size: 2.4vw;
              color: #ED6381;
              font-weight: bold;
            }
          }
        }
        .centerMapBox{
          width:100%;
          height:calc(100vh - 4.84vw - 4vw - 8.8vh - 7.7vw - 2px);
          background:url("../../assets/images/dituhou-bj.png") no-repeat;
          background-size:100% 100%;
          background-position:center center;
          margin-top:1.5vh;
          .map{
            width:80%;
            height:calc(100vh - 4.84vw - 4vw - 8.8vh - 7.7vw - 2px - 2vh);
            margin:1vh auto;
          }
        }
      }
      .rightContainer{
        padding-left:1vw;
        .evaluateBox{
          width:100%;
          height:35vh;
          padding: 2vh 1vw;
          box-sizing:border-box;
          margin-bottom:3vh;
          .pieChart{
            width:100%;
            height: 31vh;
          }
        }
        .echartsBox{
          width:100%;
          height:calc(100vh - 4.84vw - 4vw - 35vh - 2px - 3vh);
          padding: 2vh 1vw;
          box-sizing:border-box;
          .lineChart{
            width:100%;
            height: calc(100vh - 4.84vw - 4vw - 35vh - 2px - 3vh - 2vh - 4vw);
          }
        }
      }
    }
    .unifiedBorder{
      background:#07262E;
      border:1px solid #62758C;
      border-radius:3px;
      position:relative;
      .unifiedBorderBox{
        width:100%;
        height:100%;
        position:absolute;
        bottom:0;
        left:0;
      }
      .unifiedBorderBox:before{
        content:"";
        background:url("../../assets/images/hornIcon.png") no-repeat;
        background-size: 1000% 400%;
        background-position:100% 100%;
        width:15px;
        height:15px;
        position:absolute;
        bottom:-2px;
        right:-2px;
      }
      .unifiedBorderBox:after{
        content:"";
        background:url("../../assets/images/hornIcon.png") no-repeat;
        background-size: 1000% 400%;
        background-position: 0 100%;
        width:15px;
        height:15px;
        position:absolute;
        bottom:-2px;
        left:-2px;
      }
    }
    .unifiedBorder:before{
      content:"";
      background:url("../../assets/images/hornIcon.png") no-repeat;
      background-size: 1000% 400%;
      background-position:0 0;
      width:15px;
      height:15px;
      position:absolute;
      top:-2px;
      left:-2px;
    }
    .unifiedBorder:after{
      content:"";
      background:url("../../assets/images/hornIcon.png") no-repeat;
      background-size: 1000% 400%;
      background-position:100% 0;
      width:15px;
      height:15px;
      position:absolute;
      top:-2px;
      right:-2px;
    }
    .unifiedTitle{
      width:100%;
      padding-bottom: 4%;
      padding-left:8%;
      color:#28E3F2;
      font-size:1vw;
      background:url("../../assets/images/titleBg.png") no-repeat;
      background-size:100% 80%;
      background-position:bottom center;
      .activeText{
        font-weight:bold;
        color:#28E3F2;
        font-size:13px;
      }
      span{
        margin-right:5px;
        cursor:pointer;
        font-size:12px;
        color:#A5FAFF;
      }
    }
  }
</style>