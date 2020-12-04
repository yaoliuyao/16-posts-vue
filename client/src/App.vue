<template>
  <div id="app">
    <div>
      <router-link to="/home">主页</router-link>
      <router-link to="/post">详情</router-link>
    </div>
    <hr>

    <!-- 路由出口 -->
    <router-view></router-view>
  </div>
</template>

<script>
export default {
  name: 'app',
  data() {
    return {
      currentComp: "Home"
    }
  }
}
</script>

<style>
  #app {
  }
  body {
    margin: 0;
    padding: 0;
  }
</style>
