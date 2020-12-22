"use strict";

import Vue from 'vue';
import axios from "axios";
import { Message, Loading } from "element-ui";

// Full config:  https://github.com/axios/axios#request-config
axios.defaults.baseURL = '/myblog';
// axios.defaults.headers.common['Authorization'] = AUTH_TOKEN;
// axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded';

let config = {
  // baseURL: process.env.baseURL || process.env.apiUrl || ""
  timeout: 60 * 1000, // Timeout
  // withCredentials: true, // Check cross-site Access-Control
};

const _axios = axios.create(config);

let loading;

_axios.interceptors.request.use(
  function(config) {
    loading = Loading.service({ fullscreen: true });
    return config;
  },
  function(error) {
    loading.close();
    Message.error("请求中: " + error);
    return Promise.reject(error);
  }
);

// Add a response interceptor
_axios.interceptors.response.use(
  function(response) {
    loading.close();
    return response;
  },
  function(error) {
    loading.close();
    Message.info(error);
    return Promise.reject(error); // 如果 axios 的调用处存在 .catch，那么就去调用它
  }
);

Plugin.install = function(Vue, options) {
  Vue.axios = _axios;
  window.axios = _axios;
  // Vue.prototype.axios = _axios;
  // Vue.prototype.$axios = _axios;
  Object.defineProperties(Vue.prototype, {
    axios: {
      get() {
        return _axios;
      }
    },
    $axios: {
      get() {
        return _axios;
      }
    },
  });
};

Vue.use(Plugin)

export default Plugin;
