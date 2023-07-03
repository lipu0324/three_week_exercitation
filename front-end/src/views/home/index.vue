<template>
  <div class="app-container_index">
    <router-link to="dataScreen" class="jumpButton">数据大屏</router-link>
    <div class="mainDataBox">
      <div v-for="item in topData" class="mainDataItem">
        <p>{{item.title}}</p>
        <p>{{item.num}}<span>{{item.company}}</span></p>
      </div>
    </div>
    <div class="secondDataBox">
      <div v-for="item in otherData" class="secondDataItem">
        <img :src="require(`../../assets/images/${item.icon}.png`)" alt="">
        <p>{{item.title}}</p>
        <p>{{item.num}}</p>
      </div>
    </div>
    <el-row>
      <el-col :span="24" style="position:relative" id="chartBox">
        <div id="myChart" class="lineChart" ></div>
        <div class="chartsTab">
          <div v-for="item in chartTabList" class="chartsItem" :class="{'activeItem':item.value == dataSection}" @click="changeCharts(item.value)">{{item.text}}</div>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import echarts from 'echarts';
import Vue from 'vue';
import request from '@/utils/request';
import { formatDate } from '@/utils/date';
import { getIndexData } from '@/api/index.js';
Vue.prototype.$echarts = echarts
// 引入第三方js
export default {
  name: "home",
  data() {
    return {
      topData:[
        {
          title:"热线会话总量",
          num:'0',
          bgImg:'coversation',
          company:"通"
        },{
          title:"今日热线会话量",
          num:'0',
          bgImg:'hot',
          company:"通"
        },{
          title:"工单总量",
          num:'0',
          bgImg:'rxCoversation',
          company:"个"
        },{
          title:"档案总量",
          num:'0',
          bgImg:'archive',
          company:"个"
        }
      ],
      otherData:[
        {
          title:"用户总量",
          num:"0",
          icon:"user"
        },
        {
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
        },
        // {
        //   title:"在线话务员",
        //   num:"0",
        //   icon:"online"
        // },{
        //   title:"离线话务员",
        //   num:"0",
        //   icon:"outline"
        // },
        {
          title:"在线AI数量",
          num:"0",
          icon:"ai"
        }
      ],
      chartTabList:[
        {
          text:"一周",
          value:7,
        },
        {
          text:"一个月",
          value:31,
        },
        {
          text:"三个月",
          value:93,
        }
      ],
      msg_host:msg_host,
      http_urla:"http://" + msg_host + ":" + http_url,
      queryData:{
        tenantId:targetTenantId.toString(),
        actionID:exten,
        startTime:'',
        endTime: formatDate(new Date(), 'yyyy-MM-dd hh:mm:ss'),
        filter:queue[0]
      },
      dataSection:7,
      echartsData:{
        callIn:[],
        callOut:[]
      },
      echartsDate:[],
    };
  },
  mounted() {
    if(this.$route.query.reload == 1){
      let path = this.$route.path; //先获取路由路径
      this.$router.push(path);
      window.location.reload();
    }
    this.beforeGetData();
    this.getAllData();
    let that = this;
    var elementResizeDetectorMaker = require("element-resize-detector");//导入
    var erd = elementResizeDetectorMaker();
    erd.listenTo(document.getElementById("chartBox"), function (element) {
      that.$nextTick(function () {
        //使echarts尺寸重置
        that.$echarts.init(document.getElementById("myChart")).resize()
      })
    })
  }, 
  methods: {
    // 获取统计数据
    getAllData(){
      getIndexData().then((res)=>{
        // 工单数
        this.topData[2].num = res.data.workOrderCount || 0;
        // 档案数
        this.topData[3].num = res.data.archivesCount || 0;
        // 用户数
        this.otherData[0].num = res.data.memberCount || 0;
        // 部门数
        this.otherData[1].num = res.data.deptCount || 0;
        // 知识库数
        this.otherData[2].num = res.data.knowledgeCount || 0;
        // ai数
        this.otherData[4].num = res.data.aiCount || 0;
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
        this.topData[0].num = response.totalCount || 0;
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
          if(response.data.agent) _this.otherData[3].num = response.data.agent.length;
          // // 座席在线数量
          // _this.otherData[4].num = response.data[0].loginNum || 0;
          // // 座席离线数量
          // _this.otherData[5].num = response.data[0].logoutNum || 0;
        })
      },1200)
    },
    // 查询数据之前的操作
    beforeGetData(){
      this.echartsDate = [];
      let num = -(parseFloat(this.dataSection)-1); // 定义查询多少天的数据
      this.queryData['startTime'] = this.fun_date(num)+ ' 00:00:00'; // 定义开始时间
      for(let i=0;i<this.dataSection;i++){ // 处理x坐标轴
        this.echartsDate.unshift(this.fun_date(-i))
      }
      this.getCallData(); // 获取数据
    },
    // 获取今天之前的日期
    fun_date(aa){  
      var date1 = new Date(),  
      time1=date1.getFullYear()+"-"+(date1.getMonth()+1)+"-"+date1.getDate();//time1表示当前时间  
      var date2 = new Date(date1);  
      date2.setDate(date1.getDate()+aa);  
      let month = date2.getMonth()+1 > 9 ? date2.getMonth()+1 : '0'+(date2.getMonth()+1);
      let date = date2.getDate() > 9 ? date2.getDate() : '0'+date2.getDate();
      var time2 = date2.getFullYear()+"-"+month+"-"+date;  
      return time2;
    },
    // 获取echart数据
    getCallData(){
      request({
          url:`${this.http_urla}query/queryQueueDayDateQueueStatistics`,
          method:"post",
          data: this.queryData
        }).then(response => {
          this.echartsData = {
            callIn:[],
            callOut:[]
          }
          this.echartsDate.map((item,index)=>{
            let datasource = response.data ? response.data.filter(m=>m.intDay == item):[];
            let data = datasource.length > 0 ? datasource[0] : {};
            this.echartsData['callIn'].push(Math.round(data.inCalls+data.inAnswerCalls*0.2) || 0)
            this.echartsData['callOut'].push(data.inAnswerCalls || 0)
            if(index == this.echartsDate.length-1){
              // 最后一条数据为当天数据 赋值当天会话统计数量
              this.topData[1].num = data.calls || 0;
            }
          })
          this.drawLine();
        })
    },
    // 绘制表格
    drawLine(){
      // 基于准备好的dom，初始化echarts实例
      let myChart = this.$echarts.init(document.getElementById('myChart'))
      // 绘制图表
      myChart.setOption({
        grid: {
          top: '15%'
        },
        title: { 
          text: '热线量',
          top: 20,
          left: 20,
          textStyle:{
            color:'#393939',
            fontSize:14
          }
        },
        tooltip: {
          formatter:'{a}<br/>{b0}: {c0}通'
        },
        xAxis: {
          type: 'category',
          boundaryGap: false,
          data:this.echartsDate,
          axisLine:{
            show:false
          },
          axisTick:{
            show:false
          },
          axisLabel:{
            color:'#999999',
            fontSize:12
          }
        },
        yAxis: {
          name:"(通)",
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
        legend: {
          icon:'circle',
          top:20,
          textStyle:{
            color:'#999999'
          }
        },
        color:['#C88BE8','#6986FD'],
        series: [
          {
            name:"会话总量",
            data:this.echartsData.callIn,
            type:"line",
            lineStyle:{
              opacity:0.6
            },
            smooth: true,
            areaStyle: {
              opacity:0.6
            }
          },
          {
            name:"人工接听",
            data:this.echartsData.callOut,
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
      window.onresize = function () {
        myChart.resize()
      }
    },
    // 切换表格数据
    changeCharts(value){
      this.dataSection = value;
      this.beforeGetData();
    }
  },
};
</script>

<style lang="scss" scoped>
  .app-container_index {
    background: #F0F2F5;
    padding: 35px;
    box-sizing: border-box;
    .jumpButton{
      width: 80px;
      height: 30px;
      display:flex;
      align-items:center;
      justify-content:center;
      background: linear-gradient(to right,#5870FB, #C476FF);
      float:right;
      color:#fff;
      font-size:12px;
      border-radius:4px;
      margin-bottom:10px;
    }
    .mainDataBox{
      width: 100%;
      display:flex;
      align-items:center;
      justify-content:space-between;
      .mainDataItem{
        width: calc((100vw - 350px) / 4);
        height: calc(((100vw - 350px) / 4) * 0.46);
        padding: 18px 25px;
        box-sizing: border-box;
        p{
          margin: 0;
          color:#fff
        }
        p:first-child{
          font-size: 12px;
          margin-bottom: 8px;
        }
        p:last-child{
          font-size: 24px;
          font-weight: bold;
          span{
            font-size: 12px;
            font-weight:normal;
            margin-left:5px;
          }
        }
      }
      .mainDataItem:nth-child(1){
        background: url("../../assets/images/coversation.png");
        background-size: 100%;
      }
      .mainDataItem:nth-child(2){
        background: url("../../assets/images/hot.png");
        background-size: 100%;
      }
      .mainDataItem:nth-child(3){
        background: url("../../assets/images/rxCoversation.png");
        background-size: 100%;
      }
      .mainDataItem:nth-child(4){
        background: url("../../assets/images/archive.png");
        background-size: 100%;
      }
    }
    .secondDataBox{
      display: flex;
      justify-content: space-evenly;
      align-items: center;
      width: 100%;
      margin-top: 15px;
      .secondDataItem{
        width: 120px;
        text-align:center;
        img{
          width: 50px;
          height: 50px;
          margin-bottom: 5px;
        }
        p{
          margin:0;
          margin-top: 7px;
        }
        p:nth-child(2){
          font-size: 12px;
          color: #333333;
        }
        p:nth-child(3){
          font-size: 20px;
          color: #ED6381;
          font-weight: bold;
        }
      }
    }
    .lineChart{
      width:100%;
      height: 350px;
      background: #fff!important;
      border-radius:4px;
      margin-top:40px;
    }
    .chartsTab{
      position:absolute;
      top:50px;
      right: 20px;
      display: flex;
      .chartsItem{
        width: 45px;
        height: 30px;
        background:#F0F2F5;
        border-radius:4px;
        text-align:center;
        line-height:30px;
        font-size:12px;
        color:#393939;
        margin-right:12px;
        cursor: pointer;
      }
      .activeItem{
        background: #4782FF;
        color: #fff;
        box-shadow: 0px 0px 6px rgba(19,29,77,0.58);
      }
    }
  }

</style>
