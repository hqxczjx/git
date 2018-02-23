function waterFall() {
    let imageList = [
        './image/img1.jpg',
        './image/vue-logo.jpg',
        './image/P_000.jpg',
        './image/P_001.jpg',
        './image/P_002.jpg',
        './image/P_003.jpg',
        './image/P_004.jpg',
        './image/P_005.jpg',
        './image/P_006.jpg',
        './image/P_007.jpg',
        './image/P_008.jpg'
    ]
    let waterFall = document.querySelector('.water-fall');
    let clientWidth = document.documentElement.clientWidth;
    let cols = 120;
    let colsNum = Math.floor(clientWidth / (cols + 10));
    let col = new Array(colsNum);
    let row = (cols + 10) * colsNum;
    let item;

    function init() {
        for (let i = 0; i < colsNum; i++) {
            let item = document.createElement('div');
            item.classList.add('col');
            item.style.width = cols + 'px';
            col[i] = item;
            waterFall.appendChild(item);
        }
    }

    waterFall.style.width = row + 'px';
    init();
    let items = document.querySelectorAll('.col');
    for (let i = 0; i < colsNum; i++) {
        for (let j = 0; j < 5; j++) {
            let item = document.createElement('div');
            let img = document.createElement('img');
            img.src = imageList[parseInt(Math.random() * 11)];
            img.style.width = '100%';
            item.classList.add('col-item');
            item.appendChild(img);
            items[i].appendChild(item);
        }
    }
    document.addEventListener('DOMMouseScroll', function () {//ForeFox
        for (let i of items) {
            if (document.documentElement.scrollTop + document.documentElement.clientHeight - i.lastChild.offsetTop > 10) {
                let item = document.createElement('div');
                let img = document.createElement('img');
                img.src = imageList[parseInt(Math.random() * 11)];
                img.style.width = '100%';
                item.classList.add('col-item');
                item.appendChild(img);
                i.appendChild(item);
            }
        }
    });
    document.addEventListener('mousewheel', function () {// other Browser
        for (let i of items) {
            if (document.documentElement.scrollTop + document.documentElement.clientHeight - i.lastChild.offsetTop > 10) {
                let item = document.createElement('div');
                let img = document.createElement('img');
                img.src = imageList[parseInt(Math.random() * 11)];
                img.style.width = '100%';
                item.classList.add('col-item');
                item.appendChild(img);
                i.appendChild(item);
            }
        }
    });
}