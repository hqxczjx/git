/**
 * Created by ZJX on 2017/5/18.
 */
/**
 * Created by ZJX on 2017/5/17.
 */
var bookLists = [
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

var vm = new Vue({
    el: '.main',
    data: {
        list: bookLists
    }
});
