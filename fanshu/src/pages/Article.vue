<template>
  <div id="article">
    <div v-html="compiledMarkdown"></div>
  </div>
</template>

<script>
  import marked from 'marked';
  export default {
    name: 'article',
    data () {
      return {
        input: '# hello'
      }
    },
    created () {
      var path = this.$route.path.substr(6);
      this.$api.SDK.Query.doCloudQuery('select * from Article where title = ?', [path]).then((data)=>{
          this.input = data.results[0].get('content');
      });
    },
    computed: {
      compiledMarkdown: function () {
        return marked(this.input, { sanitize: true })
      }
    }
  }
</script>

<style scoped>
  html, body, #article {
    margin: 0;
    height: 100%;
    font-family: 'Helvetica Neue', Arial, sans-serif;
    color: #333;
  }

  #article div {
    display: inline-block;
    width: 49%;
    height: 100%;
    vertical-align: top;
    box-sizing: border-box;
    padding: 0 20px;
    text-align: left;
  }

  code {
    color: #f66;
  }

  #article{
    margin-top: 10px;
  }
</style>
