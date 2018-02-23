/**
 * Created by tree on 2018/1/30.
 */
module.exports = {
  /**
   * Application configuration section
   * http://pm2.keymetrics.io/docs/usage/application-declaration/
   */
  apps: [
    {
      name: 'm.block.cc',
      max_memory_restart: "800M",
      script: 'nuxt start',
      exec_mode: "fork",
      env: {
        PORT: 3002
      }
    }
  ]
};
