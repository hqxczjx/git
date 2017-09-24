<template>
  <div id="editor">
    <div>
      <input ref="title" v-model="title"/>
    </div>
    <textarea ref="info" v-if="isText" :value="input" @input="update"></textarea>
    <div class="look" v-html="compiledMarkdown"></div>
    <div class="effect">
      <button @click="wWrite">watch</button>
      <button @click="sWrite">submit</button>
      <button @click="cancel">cancel</button>
    </div>
  </div>
</template>

<script>
  import marked from 'marked';
  import AV from 'leancloud-storage';
  export default {
    name: 'editor',
    data () {
      return {
        title: 'hello',
        input: '# hello',
        isText: true
      }
    },
    computed: {
      compiledMarkdown: function () {
        return marked(this.input, { sanitize: true })
      }
    },
    methods: {
      update: function (e) {
        this.input = e.target.value
      },
      sWrite: function () {
        if(this.$refs.info.value !== '' && this.$refs.title.value !== ''){
          var user = AV.Object.createWithoutData('_User', '59b69132ac502e0060d8b39d'),
            category = AV.Object.createWithoutData('Category', '59b69157570c35006e3cd110'),
            Article = AV.Object.extend('Article'),
            article = new Article(),
            that = this;
          AV.Query.doCloudQuery('select * from Article where title = ?', [this.$refs.title.value]).then(function (data) {
            if(data.results.length < 1){
              article.set('category', category);
              article.set('content', that.$refs.info.value);
              article.set('title', that.$refs.title.value);
              article.set('author', user);
              article.save().then(function (data) {
                console.log('id' + data.id);
              },function (error) {
                console.log(error);
              });
              that.$router.push({ path: '/' });
            }
            else {
              alert('存在该标题的文章');
            }
          },function (error) {
            console.log(error);
          });
        }
        else{
          alert('nothing');
          this.$refs.info.focus();
        }
      },
      wWrite: function () {
        return this.isText = !this.isText;
      },
      cancel: function () {
        this.$router.push({ path: '/blog' });
      }
    }
  }
</script>

<style>
  html, body, #editor {
    margin: 0;
    height: 100%;
    font-family: 'Helvetica Neue', Arial, sans-serif;
    color: #333;
  }

  textarea, .look {
    display: inline-block;
    width: 49%;
    height: 100%;
    vertical-align: top;
    box-sizing: border-box;
    padding: 0 20px;
    text-align: left;
  }

  textarea {
    border: none;
    border-right: 1px solid #ccc;
    resize: none;
    outline: none;
    background-color: #f6f6f6;
    font-size: 14px;
    font-family: 'Monaco', courier, monospace;
    padding: 20px;
  }

  code {
    color: #f66;
  }

  #editor{
    margin-top: 10px;
  }

  .effect{
    width: 100%;
  }
</style>
