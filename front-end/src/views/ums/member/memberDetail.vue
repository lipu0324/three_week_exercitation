<template>
  <div>
    <el-row :gutter="12">
      <el-col :span="8">
        <el-card shadow="always" style="width: 800px">
          <span>用户信息</span>
          <el-form :data="detail" ref="productInfoForm" label-width="100px" style="width: 700px;margin-top: 20px;" size="big" label-position="left" v-if="detail">
            <el-form-item label="编号：" prop="productCategoryId">
              {{detail.id}}
            </el-form-item>
            <el-form-item label="用户名：" prop="subTitle">
              {{detail.username}}
            </el-form-item>
            <el-form-item label="头像：" prop="name">
              <img width="50" height="50" :src="detail.icon" />
            </el-form-item>
            <el-form-item label="昵称：" prop="subTitle">
              {{detail.nickname}}
            </el-form-item>
            <el-form-item label="手机号：" prop="subTitle">
              {{detail.phone}}
            </el-form-item>
            <el-form-item label="性别：" prop="brandId">
              {{sex(detail.gender)}}
            </el-form-item>
            <el-form-item label="地区：">
              {{detail.city}}
            </el-form-item>
            <el-form-item label="生日：">
              {{formatDateTime(detail.birthday)}}
            </el-form-item>
            <el-form-item label="个性签名：">
             {{detail.personalizedSignature}}
            </el-form-item>
            <el-form-item label="加好友介绍：">
              1234567890
            </el-form-item>
          </el-form>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
  import {watchMember} from '@/api/login'
  import {formatDate} from '@/utils/date';
  import { BASE_API} from '../../../../config/dev.env';
  export default {
    name:'detail',
    data(){
      return {
        detail:null,
      }
    },
    created() {
      this.watchDetail()
      // this.listLoading = false;
      // this.list = response.data.list;
      // this.total = response.data.total;
    },
    methods:{
      sex(val){
        if(val==1){
          return '男'
        }else if(val==2){
          return '女'
        }else{
          return '未知'
        }
      },
      watchDetail(){
        console.log(this.$route.query.id)
        watchMember(this.$route.query.id).then(response => {
          console.log(response.data)
          this.listLoading = false;
          this.detail = response.data;
            if(this.detail.icon==null){
              this.detail.icon='data:image/jpeg;base64,/9j/4AAQSkZJRgABAQAAAQABAAD/2wBDAAgGBgcGBQgHBwcJCQgKDBQNDAsLDBkSEw8UHRofHh0aHBwgJC4nICIsIxwcKDcpLDAxNDQ0Hyc5PTgyPC4zNDL/2wBDAQkJCQwLDBgNDRgyIRwhMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjL/wAARCAEsASwDASIAAhEBAxEB/8QAHwAAAQUBAQEBAQEAAAAAAAAAAAECAwQFBgcICQoL/8QAtRAAAgEDAwIEAwUFBAQAAAF9AQIDAAQRBRIhMUEGE1FhByJxFDKBkaEII0KxwRVS0fAkM2JyggkKFhcYGRolJicoKSo0NTY3ODk6Q0RFRkdISUpTVFVWV1hZWmNkZWZnaGlqc3R1dnd4eXqDhIWGh4iJipKTlJWWl5iZmqKjpKWmp6ipqrKztLW2t7i5usLDxMXGx8jJytLT1NXW19jZ2uHi4+Tl5ufo6erx8vP09fb3+Pn6/8QAHwEAAwEBAQEBAQEBAQAAAAAAAAECAwQFBgcICQoL/8QAtREAAgECBAQDBAcFBAQAAQJ3AAECAxEEBSExBhJBUQdhcRMiMoEIFEKRobHBCSMzUvAVYnLRChYkNOEl8RcYGRomJygpKjU2Nzg5OkNERUZHSElKU1RVVldYWVpjZGVmZ2hpanN0dXZ3eHl6goOEhYaHiImKkpOUlZaXmJmaoqOkpaanqKmqsrO0tba3uLm6wsPExcbHyMnK0tPU1dbX2Nna4uPk5ebn6Onq8vP09fb3+Pn6/9oADAMBAAIRAxEAPwD3GiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKOlABRVS4vo4eActWdLqEsh7D6UAblFc4biUn77fnTlu5VP3ifqaAOhorKh1Q5AkHHsK0o5UlXKmgB9FFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFZd9fYzHGfqas39x5MWAfmPSsMkk5NACEknJooooAKKKKACpre4eBwQeO4qGigDo7edZ49w/EVLWDZXBhlAJ+U9a3QcgGgBaKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACg8CimyHETH2NAGJqEpkuWXsp4qpT5G3SMfWmUAFFFFABRRRQAUUUUAFb1jL5tuCetYNamktksvoKANSiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigApk3+pf8A3TT6a4zGw9qAOaPBNJUky7ZmHoajoAKKKKACiiigAooooAK0tJH71z7Vm1raUmFLetAGlRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAYeoxeXcFuzHiqdb97bieI4+8OlYLKVYg9RQAlFFFABRRRQAUUUUAFb9lF5VuAetZlhbedJuYfKK3AMDFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFZeoWf/LRB9RWpTXUMpU96AOZoqW5j8u4dR0BqKgAooooAKkghaaQKPxqOtjS4QIvM7nigC3BCsMYUD61LRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABQTgZpCQBknis+9vlVSkZyT3oAoXrBrp8etV6CcnJooAKKKKACtzTXDWoHfJrDqxa3Jt5M9j1oA6CiooZ0mUFSM+lS0AFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFRyzxxDLMB7ZoAkqCe7jgHzHn0rOuNSZ8rHwPeqDMWOSc0AW7i/klOFO0e1UycmiigAooooAKKKKACiiigB8czxHKMRWnb6mrYWTg+tZNFAHTqwcAg8GlrnobqSA/KeK1LfUI5cBjtPuaALtFICCMg5FLQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUhIAyaWsi/vCzGNDx3oAnutRVMrHyaypJnlOXYn6mmUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFAODkUUUAXLe/khOGO4e5rWguEnXKmudqSKVoXDKaAOkoqC1nE8Qbv3qegAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACubm/17/7xrpK5u4UrO+fU0AR0UUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQBr6T/qX/3q0az9KUiB89zWhQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABVWexjnOTwfarVFAGd/ZEX/AD0aj+yIv+ejVo0UAZ39kRf89Go/siL/AJ6NWjRQBnf2RF/z0aj+yIv+ejVo0UAZ39kRf89Go/siL/no1aNFAGd/ZEX/AD0aj+yIv+ejVo0UAZ39kRf89Go/siL/AJ6NWjRQBnf2RF/z0aj+yIv+ejVo0UAZ39kRf89Go/siL/no1aNFAGd/ZEX/AD0alXSogc72NaFFADI41jUKo4p9FFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAf/2Q=='
            }else{
              this.detail.icon=BASE_API.slice(1,-1)+'/'+ this.detail.icon
            }
            if(this.detail.nickname==null){
              this.detail.nickname="无"
            }
            if (this.detail.phone==null) {
              this.detail.phone = "无"
            }
            if(this.detail.city==null){
              this.detail.city="无"
            }
            if(this.detail.personalizedSignature==null){
              this.detail.personalizedSignature="无"
            }
        });
      },
      formatDateTime(time) {
        if(time==null){
          return "无"
        }else {
          let date = new Date(time);
          return formatDate(date, 'yyyy-MM-dd')
        }
      }
    }
  }
</script>

<style scoped>
  div{
    width: 600px;
    margin-top: 10px;
    margin-left: 80px
  }
  span{
    margin-top: 10px;
    font-size: 20px;
  }
  img{
    border-radius: 50%;
  }
</style>
