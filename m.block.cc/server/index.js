const Koa = require('koa')
const session = require('koa-session')
const axios = require('axios')
// import { Nuxt, Builder } from 'nuxt'
const { Nuxt, Builder } = require('nuxt')

async function start () {
  const app = new Koa()
  const host = process.env.HOST || '0.0.0.0'
  const port = process.env.PORT || 3002
  // process.env.DEBUG = 'nuxt:*'

  // Import and Set Nuxt.js options
  let config = require('../nuxt.config.js')
  config.dev = !(app.env === 'production')

  // Instantiate nuxt.js
  const nuxt = new Nuxt(config)

  // Build in development
  if (config.dev) {
    const builder = new Builder(nuxt)
    await builder.build()
  }

  app.use(session(app));
  // let lan = 'en';
  app.use(async (ctx, next) => {
    
    // if (ctx.cookies.get('lan') === undefined) {
    //   ctx.cookies.set('lan', 'zh', {key: 'lan'});
    // }
    
    // if (ctx.cookies.get('local') !== undefined && ctx.cookies.get('local') !== ctx.cookies.get('lan')) {
    //   console.log(ctx.cookies.get('local'));
      
    //   ctx.cookies.set('lan', ctx.cookies.get('local'), {key: 'lan'});
    // }
    // console.log(axios.defaults.headers);
    // let temp = ctx.req.headers.cookie;
    // for(let i of temp) {
    //   if (i.split('=')[0] === 'lan' && i.split('=') !== lan) {
    //     lan = i.split('=')[1];
    //     break;
    //   }
    // }
    // console.log(temp + 'from server');
    
    await next();
  });

  app.use(async (ctx, next) => {
    await next()
    ctx.status = 200 // koa defaults to 404 when it sees that status is unset
    return new Promise((resolve, reject) => {
      ctx.res.on('close', resolve)
      ctx.res.on('finish', resolve)
      
      nuxt.render(ctx.req, ctx.res, promise => {
        // nuxt.render passes a rejected promise into callback on error.
        promise.then(resolve).catch(reject)
      })
    })
  })

  app.listen(port, host)
  console.log('Server listening on ' + host + ':' + port) // eslint-disable-line no-console
}

start()
