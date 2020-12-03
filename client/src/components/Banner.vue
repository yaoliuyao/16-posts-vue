<template>
    <div class="banner">
        <div class="hot-tags">
            热门标签
        </div>
        <div class="hot-posts">
            点击排行
        </div>
    </div>
</template>

<script>
    export default {
        name: "Banner"
    }
</script>

<style scoped>
    .hot-tags, .hot-posts {
        height: 300px;
        border: 1px solid blue;
        padding: 2em;
    }
</style>