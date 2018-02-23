/**
 * Created by tree on 2018/1/29.
 */
import Vue from 'vue';

Vue.mixin({
  methods: {
    $setSeo (title, content,keywords) {
      return {title: title, meta: [{hid: 'description', name: 'description', content: content},
        {hid: 'keywords', name: 'keywords', content: keywords}]}
    },
  }
});
