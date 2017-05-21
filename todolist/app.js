var store={
	save(key, value){
		localStorage.setItem(key, JSON.stringify(value));
	},
	fetch(key){
		return JSON.parse(localStorage.getItem(key)) || [];
	}
};

var list = store.fetch("miaov-new-class");

var filter = {
    all:function (list) {
        return list;
    },
    finished:function (list) {
        return list.filter(function (item) {
            return item.isChecked;
        });
    },
    unfinished:function (list) {
        return list.filter(function (item) {
            return !item.isChecked;
        });
    }
};

var vm = new Vue({
	el:'.main',
	data:{
		list:list,
		todo:'',
		edtorTodos:'',
		beforeTitle:'',
		visibility:'all'
	},
	methods:{
		addTodo(){
			this.list.push({
				title:this.todo,
				isChecked:false
			});
			this.todo = '';
		},
		deleteTodo(todo){
			var index = this.list.indexOf(todo);
			this.list.splice(index,1);
		},
		edtorTodo(todo){
            this.beforeTitle = todo.title;
			this.edtorTodos = todo;
		},
		edtorTodoed(todo){
			this.edtorTodos = '';
		},
		cancelTodo(todo){
			todo.title = this.beforeTitle;
			this.beforeTitle = '';
			this.edtorTodos = '';
		}
	},
	directives:{
		'focus':{
			update(el,binding){
				if(binding.value){
					el.focus();
				}
			}
		}
	},
	computed:{
		noCheckLength:function () {
            return this.list.filter(function(item){
                return !item.isChecked;
            }).length
        },
		filteredList:function () {
			return filter[this.visibility] ? filter[this.visibility](list) : list;
        }
	},
	watch:{
		list:{
			handler:function () {
				store.save("miaov-new-class", this.list);
            },
			deep:true
		}
	}
});

function watchHashChange() {
    var hash = window.location.hash.slice(1);
    vm.visibility = hash;
}

watchHashChange();

window.addEventListener('hashchange',watchHashChange);