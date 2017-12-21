class Point {
    constructor() {
        this.canvas = document.querySelector('.point-line'); //canvas元素
        this.ctx = this.canvas.getContext('2d'); //canvas上下文
        this.x; //x轴坐标
        this.y; //y轴坐标
        this.r; //点的半径
        this.sx; //水平移动距离
        this.sy; //垂直移动距离
    }

    //初始化点
    init() {
        this.x = Math.random() * 800;
        this.y = Math.random() * 800;
        this.r = Math.random() * 2 + 1;
        this.sx = Math.random() * (-1) + 0.5;
        this.sy = Math.random() * (-1) + 0.5;
        this.ctx.beginPath();
        this.ctx.arc(this.x, this.y, this.r, 0, Math.PI * 2, true);
        this.ctx.fillStyle = `rgba(255, 255, 255, 0.8)`;
        this.ctx.fill();
    }

    line(point) {
        let distance = Math.sqrt((this.x - point.x) * (this.x - point.x) + (this.y - point.y) * (this.y - point.y));
        if (distance < 100) {
            this.ctx.beginPath();
            this.ctx.moveTo(this.x, this.y);
            this.ctx.lineTo(point.x, point.y);
            this.ctx.strokeStyle = `rgba(128, 128, 128, ${(100 - distance) / 100})`;
            this.ctx.strokeWidth = 1;
            this.ctx.stroke();
            this.ctx.closePath();
        }
    }

    //更新点位置
    update() {
        this.x += this.sx;
        this.y += this.sy;
        if (this.x > 800 || this.x < 0 || this.y > 800 || this.y < 0) {
            this.init();
        }
        this.ctx.beginPath();
        this.ctx.arc(this.x, this.y, this.r, 0, Math.PI * 2, true);
        this.ctx.fillStyle = `rgba(255, 255, 255, 0.8)`;
        this.ctx.fill();
    }

    clear() {
        this.ctx.clearRect(0, 0, 800, 800);
    }
}

function pointLine() {
    let point = [];

    function animateUpdate() {
        point[0].clear();
        for (let i = 0; i < 100; i++) {
            point[i].update();
        }
        for (let i = 0; i < 100; i++) {
            for (let j = i; j < 100; j++) {
                point[i].line(point[j]);
            }
        }
        requestAnimationFrame(animateUpdate);
    }

    for (let i = 0; i < 100; i++) {
        point[i] = new Point();
    }
    for (let i = 0; i < 100; i++) {
        point[i].init();
    }
    requestAnimationFrame(animateUpdate);
}