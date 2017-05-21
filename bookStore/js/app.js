/**
 * Created by ZJX on 2017/5/17.
 */
//total
var totalBookLists = [
    {message:'皮囊',author:'我',price:'1',img:'images/pinang.jpg',FL:'小说',fl:'xs',info:'别看了你是看不到有什么东西的了，因为我都没存进去，死心吧！'},
    {message:'活着',author:'我',price:'11',img:'images/img2.jpg',FL:'教育',fl:'jy',info:'别看了你是看不到有什么东西的了，因为我都没存进去，死心吧！'},
    {message:'神曲',author:'我',price:'12',img:'images/img3.jpg',FL:'小说',fl:'xs',info:'别看了你是看不到有什么东西的了，因为我都没存进去，死心吧！'},
    {message:'高数',author:'我',price:'13',img:'images/img4.jpg',FL:'科技',fl:'kj',info:'别看了你是看不到有什么东西的了，因为我都没存进去，死心吧！'},
    {message:'大英',author:'我',price:'14',img:'images/img5.jpg',FL:'心理学',fl:'xl',info:'别看了你是看不到有什么东西的了，因为我都没存进去，死心吧！'},
    {message:'JAVA',author:'我',price:'15',img:'images/img6.jpg',FL:'历史',fl:'ls',info:'别看了你是看不到有什么东西的了，因为我都没存进去，死心吧！'},
    {message:'Android',author:'我',price:'16',img:'images/img3.jpg',FL:'科技',fl:'kj',info:'别看了你是看不到有什么东西的了，因为我都没存进去，死心吧！'},
    {message:'PHP',author:'我',price:'17',img:'images/img4.jpg',FL:'科技',fl:'kj',info:'别看了你是看不到有什么东西的了，因为我都没存进去，死心吧！'},
    {message:'JavaScript',author:'我',price:'18',img:'images/img6.jpg',FL:'小说',fl:'xs',info:'别看了你是看不到有什么东西的了，因为我都没存进去，死心吧！'},
];
var userR = [
    {name:'jack',email:'baidu@gmail.com',password:'123456',coll:[totalBookLists[0]]},
    {name:'haha',email:'110@gmail.com',password:'123456',coll:[totalBookLists[1]]}
];
for(let i = 0; i < totalBookLists.length; i++){
    if(!window.localStorage.getItem(totalBookLists[i].message)){
        window.localStorage.setItem(totalBookLists[i].message, JSON.stringify(totalBookLists[i]));
    }
}
for(let i = 0; i < userR.length; i++){
    if(!window.localStorage.getItem(userR[i].name)){
        window.localStorage.setItem(userR[i].name, JSON.stringify(userR[i]));
    }
}
//add



//classification
var bookLists = [
    {message:'皮囊',price:'1',img:'images/pinang.jpg',fl:'xs'},
    {message:'活着',price:'11',img:'images/img2.jpg',fl:'jy'},
    {message:'神曲',price:'12',img:'images/img3.jpg',fl:'xs'},
    {message:'高数',price:'13',img:'images/img4.jpg',fl:'kj'},
    {message:'大英',price:'14',img:'images/img5.jpg',fl:'xl'},
    {message:'JAVA',price:'15',img:'images/img6.jpg',fl:'ls'},
    {message:'Android',price:'16',img:'images/img3.jpg',fl:'kj'},
    {message:'PHP',price:'17',img:'images/img4.jpg',fl:'kj'},
    {message:'JavaScript',price:'18',img:'images/img6.jpg',fl:'xs'},
];

var filter = {
    all:function (list) {
        return list;
    },
    xs:function (list) {
        return list.filter(function (item) {
            return item.fl === 'xs';
        });
    },
    jy:function (list) {
        return list.filter(function (item) {
            return item.fl === 'jy';
        });
    },
    kj:function (list) {
        return list.filter(function (item) {
            return item.fl === 'kj';
        });
    },
    ls:function (list) {
        return list.filter(function (item) {
            return item.fl === 'ls';
        });
    },
    xl:function (list) {
        return list.filter(function (item) {
            return item.fl === 'xl';
        });
    }
};

var vmClass = new Vue({
    el: '.mainClass',
    data: {
        list: bookLists,
        visibility: 'all',
        bookfl: ''
    },
    methods :{
        getFl:function (message) {
            this.bookfl = message;
        }
    },
    computed: {
        filteredList: function () {
            return filter[this.visibility] ? filter[this.visibility](bookLists) : bookLists;
        }
    }
});

function watchHashChange() {
    var hash = window.location.hash.slice(1);
    vmClass.visibility = hash;
}

watchHashChange();

window.addEventListener('hashchange',watchHashChange);



//hot
var bookLists1 = [
    {message:'皮囊',price:'1',img:'images/pinang.jpg'},
    {message:'活着',price:'11',img:'images/img2.jpg'},
    {message:'神曲',price:'12',img:'images/img3.jpg'},
    {message:'高数',price:'13',img:'images/img4.jpg'},
    {message:'大英',price:'14',img:'images/img5.jpg'},
    {message:'JAVA',price:'15',img:'images/img6.jpg'},
    {message:'Android',price:'16',img:'images/img3.jpg'},
    {message:'PHP',price:'17',img:'images/img4.jpg'},
    {message:'JavaScript',price:'18',img:'images/img6.jpg'},
];

bookLists1 = totalBookLists;

var vmHot = new Vue({
    el: '.mainHot',
    data: {
        list: bookLists1
    }
});



//person
var bookLists2 = [
    {message:'皮囊',price:'1',img:'images/pinang.jpg'},
    {message:'活着',price:'11',img:'images/img2.jpg'},
    {message:'神曲',price:'12',img:'images/img3.jpg'},
    {message:'高数',price:'13',img:'images/img4.jpg'},
    {message:'大英',price:'14',img:'images/img5.jpg'},
    {message:'JAVA',price:'15',img:'images/img6.jpg'},
    {message:'Android',price:'16',img:'images/img3.jpg'},
    {message:'PHP',price:'17',img:'images/img4.jpg'},
    {message:'JavaScript',price:'18',img:'images/img6.jpg'},
];

var vmPerson = new Vue({
    el: '.mainPerson',
    data: {
        list: JSON.parse(window.localStorage.getItem(window.localStorage.getItem('status')))
    }
});



//bookDetails
function watchHash() {
    var vmBookHash = window.location.hash.slice(1);
    if(vmBookHash !== null){
        var tempList = JSON.parse(window.localStorage.getItem(vmBookHash));
        bookLists3 = [];
        bookLists3.push(tempList);
    }
}
var bookLists3 = [
    {message:'皮囊',price:'1',img:'images/pinang.jpg'},
    {message:'活着',price:'11',img:'images/img2.jpg'},
    {message:'神曲',price:'12',img:'images/img3.jpg'},
    {message:'高数',price:'13',img:'images/img4.jpg'},
    {message:'大英',price:'14',img:'images/img5.jpg'},
    {message:'JAVA',price:'15',img:'images/img6.jpg'},
    {message:'Android',price:'16',img:'images/img3.jpg'},
    {message:'PHP',price:'17',img:'images/img4.jpg'},
    {message:'JavaScript',price:'18',img:'images/img6.jpg'},
];
watchHash();

var vmBook = new Vue({
    el: '.mainBook',
    data: {
        list: bookLists3
    }
});