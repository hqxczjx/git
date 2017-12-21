function iWantTo() {
    let img = new Image();
    //img.crossOrigin = 'Anonymous'; //解决跨域问题
    let btn = document.querySelector('.sub');//查看效果
    let update = document.querySelector('.update');//重新输入
    let outPut = document.querySelector('.output');//生成图片
    let closeImg = document.querySelector('.img-show>span');//关闭图片
    let showImg = document.querySelector('.img-show');//弹出层
    let show = document.querySelector('.img-show>img');//弹出图片
    let input = document.querySelector('textarea[name=input]');
    let canvas = document.querySelector('canvas.i-want-to');
    let ctx = canvas.getContext('2d');
    img.onload = function () {
        try {
            canvas.width = img.width;
            canvas.height = img.height;
            ctx.drawImage(img, 0, 0);
        } catch (e) {
            console.log(e);
        }
    }
    img.src = './image/want.jpg';
    btn.addEventListener('click', function(){
        let txt = input.value;
        let initHeight = 360;//文本开始的高度
        let txtWidth = 200;//文本宽度
        let lineHeight = 25;//文本高度
        let subText = 0;//每行文本开始的序号
        let init = 0;//每行的文本序号
        let row = 0;//文本行数
        input.style.visibility = 'hidden';
        ctx.font = `${lineHeight}px Courier New`;
        for(let i = 0; i < txt.length; i++){
            if(row >= 3) {
                break;
            }
            init += ctx.measureText(txt[i]).width;
            if(init > txtWidth) {
                ctx.fillStyle = '#000000';
                ctx.fillText(txt.substring(subText, i), 30, initHeight);
                init = 0;
                subText = i;
                initHeight += lineHeight;
                row++;
            }
            if(i === txt.length - 1) {
                ctx.fillStyle = '#000000';
                ctx.fillText(txt.substring(subText, i + 1), 30, initHeight);
                row++;
            }
        }
        outPut.removeAttribute('disabled');
    });
    update.addEventListener('click', function(){
        input.style.visibility = 'visible';
        outPut.disabled = 'true';
        ctx.fillStyle = '#ffffff';
        ctx.fillRect(30, 340, 210, 60);
    });
    outPut.addEventListener('click', function(){
        show.src = canvas.toDataURL('image/jpg');
        showImg.style.display = 'block';
    });
    closeImg.addEventListener('click', function(){
        showImg.style.display = 'none';
    });
}