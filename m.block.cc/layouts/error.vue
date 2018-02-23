<template>
  <section class="container">
    <div v-if="error.statusCode === 404">
      <h1 class="title">
        404
      </h1>
      <h2 class="info">
        当前访问页面不存在
      </h2>
      <nuxt-link class="button" to="/">
        返回首页
      </nuxt-link>
    </div>
    <div v-else>
      <h2>遇到一些无法预测的错误，请稍后重试</h2>
    </div>
  </section>
</template>
<script>
export default {
  props: ['error']
}
</script>

<style scoped>
.title
{
  margin-top: 15px;
  font-size: 5em;
}
.info
{
  font-weight: 300;
  color: #9aabb1;
  margin: 0;
}
.button
{
  margin-top: 50px;
  text-decoration: none;
}
</style>
