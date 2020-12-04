<template>
  <div id="app">
    <div>
      <el-button @click="currentComp = 'Home'">显示首页</el-button>
      <el-button @click="currentComp = 'Post'">显示详情页面</el-button>
      <el-button @click="currentComp = ''">其他乱七八糟</el-button>
    </div>
    <hr>

    <!-- 切换显示 -->
    <component :is="currentComp"></component>
  </div>
</template>

<script>
import Home from './pages/Home.vue'
import Post from './pages/Post.vue'

export default {
  name: 'app',
  components: {
    Home, Post
  },
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
