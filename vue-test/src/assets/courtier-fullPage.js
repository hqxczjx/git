function fullPage(item) {
    /**
     * from courtier,2017
     * 全屏滚动效果
     * item传入页面数量
     * height为浏览器高度
     * timer为控制监听滚动事件的次数，防止事件执行过多，每一次滚动的事件执行时设为非null，
     * 等事件执行完再设回null（生产者消费者中的生产消费不均衡问题）
     * run为动画效果requestAnimationFrame返回的id，用于递归以及取消循环
     * page为当前显示的页面序号
     * maxPage为最多页面数
     */
    let init = {
        timer: null,
        page: 0
    };
    let body = document.body;
    let scroll = document.querySelector('.scroll');
    let height = body.clientHeight;
    // let timer = null;
    let run;
    // let page = 0;
    let maxPage = item;

    //每次滚动时右侧小圆点跟着页面（page）变换
    function addActive(changeItem) { //跳转的页面序号
        console.log(changeItem);
        let i = document.querySelectorAll('.scroll>i');
        i.forEach((val) => {
            val.className = '';
        });
        i[changeItem].className += 'active';
    };

    function changePage(page, nextPage) {
        console.log(page);
        console.log(nextPage);
        if (nextPage > page) {
            let runPage = nextPage;
            console.log('run:' + runPage);
            let runHeight = 15; //滚动距离
            let scrollTop; //已滚动的滚动条上方距离
            let height = document.body.clientHeight; //屏幕可视区域高度
            //let run; //动画函数名
            run = requestAnimationFrame(function fn() {
                scrollTop = document.documentElement.scrollTop;
                if (scrollTop < height * runPage && nextPage < maxPage) {
                    if (scrollTop + runHeight <= height * runPage) {
                        // scrollHeight += runHeight;
                        document.documentElement.scrollTop += runHeight;
                    } else {
                        document.documentElement.scrollTop = height * runPage;
                    }
                    run = requestAnimationFrame(fn);
                } else {
                    cancelAnimationFrame(run);
                    console.log('pre:' + init.timer);
                    init.timer = null;
                    console.log('now:' + init.timer);
                    console.log(nextPage);
                    if(nextPage < maxPage) {
                        addActive(nextPage);
                        init.page = nextPage;
                    }
                }
            });
        } else if (nextPage < page) {
            let runPage = nextPage;
            console.log('run:' + runPage);
            let runHeight = 15; //滚动距离
            let scrollTop; //已滚动的滚动条上方距离
            let height = document.body.clientHeight; //屏幕可视区域高度
            //let run; //动画函数名
            run = requestAnimationFrame(function fn() {
                scrollTop = document.documentElement.scrollTop;
                if (scrollTop > height * runPage && nextPage >= 0) {
                    if (scrollTop - runHeight >= height * runPage) {
                        // scrollHeight += runHeight;
                        document.documentElement.scrollTop -= runHeight;
                    } else {
                        document.documentElement.scrollTop = height * runPage;
                    }
                    run = requestAnimationFrame(fn);
                } else {
                    cancelAnimationFrame(run);
                    console.log('pre:' + init.timer);
                    init.timer = null;
                    console.log('now:' + init.timer);
                    console.log(nextPage);
                    if(nextPage >= 0) {
                        addActive(nextPage);
                        init.page = nextPage;
                    }
                }
            });
        }
    }

    function ev(e) {
        //取消默认的事件
        e.preventDefault();
        //若事件仍在执行则返回
        if (init.timer !== null) {
            return;
        }
        let wheel;
        if (e.keyCode === 40) {
            wheel = 1;
        } else if (e.keyCode === 38) {
            wheel = -1;
        } else if (e.wheelDelta) {
            wheel = e.wheelDelta * -1;
        } else {
            wheel = e.detail;
        }
        let runHeight = 15; //每次滚动的高度
        if (wheel > 0) {
            //向下滚动
            console.log('down');
            //设置timer=1表示事件开始执行
            init.timer = 1;
            console.log('init:' + init.timer + ' ' + init.page);
            changePage(init.page, init.page + 1);
            console.log('initNow:' + init.timer);
            // height = body.clientHeight;
            // let scrollHeight = height; //已经滚动的高度
            // run = requestAnimationFrame(function fn() { //滚动动画
            //     if (document.documentElement.scrollTop < height * page) { //已经滚动的高度小于浏览器高度*页面序号，即滚动到下一页
            //         if (scrollHeight - runHeight >= 0) { //判断已滚动高度是否大于每次滚动的高度
            //             scrollHeight -= runHeight;
            //         } else {
            //             runHeight = scrollHeight;
            //         }
            //         document.documentElement.scrollTop += runHeight; //滚～
            //         run = requestAnimationFrame(fn); //递归下一次事件
            //     } else { //若已经跳转到下一页则取消动画事件
            //         cancelAnimationFrame(run);
            //         //设置timer=null表示事件已执行完成，可以监听下一次的滚动事件
            //         timer = null;
            //         addActive(page); //设置小圆点位置
            //         if (page !== maxPage - 1) { //设置页面序号
            //             page++;
            //         }
            //         // console.log('done');
            //         // console.log(document.documentElement.scrollTop);
            //         // console.log(height);
            //         // console.log(height * page);
            //     }
            // });
        } else if (wheel < 0) {
            //向上滚动
            console.log('up');
            init.timer = 1;
            console.log('init:' + init.timer);
            changePage(init.page, init.page - 1);
            console.log('initNow:' + init.timer);
            // height = body.clientHeight;
            // let scrollHeight = height;
            // run = requestAnimationFrame(function fn1() {
            //     if (document.documentElement.scrollTop > height * (page - 1)) {
            //         if (scrollHeight - runHeight >= 0) {
            //             scrollHeight -= runHeight;
            //         } else {
            //             runHeight = scrollHeight;
            //         }
            //         document.documentElement.scrollTop -= runHeight;
            //         run = requestAnimationFrame(fn1);
            //     } else {
            //         cancelAnimationFrame(run);
            //         timer = null;
            //         if (page !== 1) {
            //             page--;
            //             addActive(page);
            //         } else {
            //             addActive(page - 1);
            //         }
            //         // console.log('done');
            //         // console.log(document.documentElement.scrollTop);
            //         // console.log(height);
            //         // console.log(height * page);
            //     }
            // });
        }
    }
    //获取页面数量初始化右侧小圆点
    for (let i = 0; i < item; i++) {
        scroll.appendChild(document.createElement('i'));
    }
    //初始化右侧小圆点，初始为0
    addActive(0);
    //监听滚动事件
    document.querySelector('.full').addEventListener('DOMMouseScroll', ev, false); //FireFox
    document.querySelector('.full').addEventListener('mousewheel', ev, false); //other Browser
    //监听键盘上下事件
    document.addEventListener('keydown', function (e) {
        if (e.keyCode === 40) {
            ev(e);
        } else if (e.keyCode === 38) {
            ev(e);
        }
    }, false);

    let icon = document.querySelectorAll('.scroll>i');
    for (let i = 0, length = icon.length; i < length; i++) {
        icon[i].addEventListener('click', function () {
            // addActive(i);
            // let runHeight = 15;
            changePage(init.page, i);
            // if (i + 1 > page) {
            //     console.log('down');
            //     //设置timer=1表示事件开始执行
            //     timer = 1;
            //     height = body.clientHeight;
            //     let scrollHeight = height * i; //已经滚动的高度
            //     console.log(height * i);
            //     run = requestAnimationFrame(function fn2() { //滚动动画
            //         if (document.documentElement.scrollTop < height * i) { //已经滚动的高度小于浏览器高度*页面序号，即滚动到下一页
            //             if (scrollHeight - runHeight >= 0) { //判断已滚动高度是否大于每次滚动的高度
            //                 scrollHeight -= runHeight;
            //             } else {
            //                 runHeight = scrollHeight;
            //             }
            //             document.documentElement.scrollTop += runHeight; //滚～
            //             run = requestAnimationFrame(fn2); //递归下一次事件
            //         } else { //若已经跳转到下一页则取消动画事件
            //             cancelAnimationFrame(run);
            //             //设置timer=null表示事件已执行完成，可以监听下一次的滚动事件
            //             timer = null;
            //             //addActive(page); //设置小圆点位置
            //             // if (page !== maxPage - 1) { //设置页面序号
            //             //     page++;
            //             // }
            //             page = i + 1;
            //             // console.log('done');
            //             // console.log(document.documentElement.scrollTop);
            //             // console.log(height);
            //             // console.log(height * page);
            //         }
            //     });
            // } else if (i < page) {
            //     console.log('up');
            //     timer = 1;
            //     height = body.clientHeight;
            //     let scrollHeight = height;
            //     run = requestAnimationFrame(function fn3() {
            //         if (document.documentElement.scrollTop > height * (i)) {
            //             if (scrollHeight - runHeight >= 0) {
            //                 scrollHeight -= runHeight;
            //             } else {
            //                 runHeight = scrollHeight;
            //             }
            //             document.documentElement.scrollTop -= runHeight;
            //             run = requestAnimationFrame(fn3);
            //         } else {
            //             cancelAnimationFrame(run);
            //             timer = null;
            //             page = i + 1;
            //             // if (page !== 1) {
            //             //     page--;
            //             //     addActive(page);
            //             // } else {
            //             //     addActive(page - 1);
            //             // }
            //             // console.log('done');
            //             // console.log(document.documentElement.scrollTop);
            //             // console.log(height);
            //             // console.log(height * page);
            //         }
            //     });
            // }
        });
    }
}

export {fullPage}