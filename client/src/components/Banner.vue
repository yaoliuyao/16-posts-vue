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
    .banner {
        padding: 1em;
    }
    .hot-tags, .hot-posts {
        height: 200px;
        padding: 1em;
        margin: 1em 0;
        box-shadow: 0 0 2px #666;
        border-radius: 5px;
    }
</style>