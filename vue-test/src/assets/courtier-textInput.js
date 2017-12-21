function textOutPut(result) {
    /**
     * from courtier,2017
     * 文本逐个显示效果
     * result传入文本内容
     * text为文本显示位置
     * raf为文本最后的闪烁光标
     * i为显示文本序号
     * ami为闪烁光标动画
     * timer记录光标闪烁所用的定时器id
     */

    let text = document.querySelector('.text-output');
    let raf = document.querySelector('.raf');
    let msg = result || 'abcdefghijkl';
    let i = 0;
    let timer = null;

    function ami() {
        raf.style.visibility = (raf.style.visibility === 'visible' ? 'hidden' : 'visible');
        if (msg.length === i) {
            //文本输入完后光标隐藏
            raf.style.visibility = 'hidden';
        }
    };

    function done() {
        if (msg.length > i) {
            i++;
            text.innerText = msg.substring(0, i);
            //如果文字正在输入，则光标显示，并清除闪烁事件重新开始
            raf.style.visibility = 'visible';
            clearInterval(timer);
            timer = setInterval(ami, 1000);
            setTimeout(done, 1000);
        } else {
            //文本输入完后清除闪烁事件
            clearInterval(timer);
            timer = null;
        }
    }

    timer = setInterval(ami, 500);
    setTimeout(done, 500);
}

export {textOutPut}