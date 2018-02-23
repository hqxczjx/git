/**
 * Created by ZJX on 2018/1/12.
 */
function format(num, lan = 'zh', isPrice = false) {
  let unit = ''
  let mod = 0
  if (isPrice === false) {
    if (lan === 'zh') {
      if (num >= 1e8) {
        mod = num % 1e8
        num /= 1e8
        unit = '亿'
      } else if (num >= 1e4) {
        mod = num % 1e4
        num /= 1e4
        unit = '万'
      }
    } else if (lan === 'en') {
      if (num >= 1e6) {
        mod = num % 1e6
        num /= 1e6
        unit = 'm'
      } else if (num >= 1e3) {
        mod = num % 1e3
        num /= 1e3
        unit = 'k'
      }
    }
    if (mod.toString().split('')[2] >= 5) {
      mod = mod.toString().split('')[0] + mod.toString().split('')[1]
      mod = (Number(mod) + 1).toString()
    }
  }
  if (num > 1) {
    num = parseFloat(num).toFixed(2)
  }
  let data = (num || 0).toString()
  let sm, big
  let result = []
  if (data.indexOf('.') > -1) {
    big = data.slice(0, data.indexOf('.'))
    sm = data.slice(data.indexOf('.') + 1)
  } else {
    big = data
    sm = '00'
  }
  big = big.split('')
  let sum = 0
  while (big.length !== 0) {
    if (sum === 3) {
      result.unshift(',')
      sum = 0
    }
    result.unshift(big.pop())
    sum++
  }
  let temp = sm.split('').findIndex((val) => {
    return val > 0
  })
  if (temp >= 0) {
    sm = sm.slice(0, temp + 2)
  }
  return {
    num: result.join('') + '.' + sm,
    unit: unit
  }
}
export default format
