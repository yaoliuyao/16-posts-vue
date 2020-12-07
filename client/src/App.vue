<template>
  <div id="app">
    <!-- 路由出口 -->
    <router-view></router-view>
    <!-- 滚动到头部 -->
    <el-backtop :visibility-height="60">
      <i class="el-icon-top"></i>
    </el-backtop>
  </div>
</template>

<script>
export default {
  name: 'app'
}
</script>

<style>
  #app {
  }
  body {
    margin: 0;
    padding: 0;
  }
  .el-backtop {
    background: darkgreen;
  }
  .el-icon-top:before {
    color: white;
    font-weight: bolder;
  }
</style>
