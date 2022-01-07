# Vue.js - day01

## 插件安装推荐

vscode有二个常用插件：vetur，Vue 2 Snippets

1. vetur:让一些vue的关键字能高亮显示，还能进行一些语法的检测。

   - 语法错误检查，包括 CSS/SCSS/LESS/Javascript/TypeScript等
   - 语法高亮，包括 html/css/sass/scss/less/js/ts等

2. Vue 2 Snippets：能在平时写代码过程中提示功能更强大,对vue的语法有提示

   官网地址(里面列出了相关提示快捷词)： [https://marketplace.visualstudio.com/items?itemName=hollowtree.vue-snippets](https://marketplace.visualstudio.com/items?itemName=hollowtree.vue-snippets) 

![image-20191119103749989](image-20191119103749989.png)





安装方式有二种：在线安装（能上网，条件允许推荐在线安装）,离线安装

**在线安装**：在vscode中按（ctrl+shift+x）,搜索插件名称（例：vetur），

​                  会出现相关插件，如果里面有install.就点击安装，如果安装完成就没install了

**离线安装**：拷贝相应的压缩包解压到相应目录下面（2个vscode插件.zip）

```
C:\Users\电脑用户名\.vscode\extensions
```

![image-20191108110536413](image-20191108110536413.png)



安装完成后，重启一下vscode就可使用插件了

​                   

## vue.js是什么

[官网地址](https://cn.vuejs.org/v2/guide/)

> 我们学习一个知识，首先是不是要了解它是什么？有什么特点？

**vue.js是什么：vue就是一个javascript框架，**

**特点：**无需再操作dom,只关心数据

vue做法与传统js对比

```html
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>

<body>
    <!-- js传统做法 -->
    <input type="text" id="txt">
    <p id="p">您输入了:</p>
    <script>
        let _txt = document.getElementById("txt")
        let _p = document.getElementById("p")
        _txt.oninput = function () {
            _p.innerText = "您输入了:" + _txt.value
        }
    </script>

    <!-- vue做法 -->
    <div id="app">
        <input type="text" v-model="msg">
        <p>您输入了:{{msg}}</p>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
    <script>
        new Vue({
            el: "#app",
            data: {
                msg: "hello"
            }
        })
    </script>
</body>

</html>
```

**总结：对vue而言，我们无需再操作dom,只需要关心数据data,把相应的数据与标签用vue的语法关联上就OK了**

## vue的基本使用

>我们刚子解了vue是什么和它的特点，那我们如何去使用它呢？

[直通车](https://cn.vuejs.org/v2/guide/#起步)

**基本使用方法（分三步）：**

- 引包(导入vue.js到html)

  - 引包这里官网说到二个词，开发环境，生产环境

    - 开发环境：就是我们作为开发人员写代码过程中使用的环境,选择包相当于jquery时的jquery.min.js。
    - 生产环境：项目正式上线后的环境,选择包相当于jquery时的jquery.js。

    学习阶段我们还是用开发环境的包，因为代码会有相关警告提示等。

  ```html
  <script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
  ```

- 布局。（写好页面标签，以前怎么写现在还怎么写）例：

  ```html
      <div id="app">
          {{msg}}
      //这个div写了一个id,就是留这个id以便vue更方便vue实例化时方便获取
      </div>
  ```

- vue实例化

  ```javascript
      <script>
          new Vue({
              //这里el相当于确定实例化范围就是id为app的标签，后面写法相当于一个选择器，注：这里不能选择body与html,尽量写一个id标签供选择，独一无二，可读性强，规范。  
              el: "#app",
              //这里的data就是vue需要使用到的数据，vue是以数据驱动页面，数据就来自于这里
              data: {
                  msg: "hello"
              }
          })
      </script>
  ```
```html
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>

<body>
    <!-- 基本使用分为三步：
1：导包；2：布局；3：实例化
-->

    <!-- 2:布局 -->
    <div id="app">
        <!-- vue的一个插值语法，相当于挖个坑，用下面的就是值来填上 -->
        {{ message }}message
        <div>{{message}}</div>
    </div>



    <!-- 1:导包(开发环境的包) -->
    <script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
    <script>
        //3:实例化过程    
        var app = new Vue({
            el: '#app',//el实际就是element，选择标签  实际就是确定vue的使用范围  ,注意这里不能选择 <html> or <body>      
            data: {      //数据存放的地方
                message: 'Hello Vue!',
                txt: ""
            }
        })
    </script>
</body>

</html>
```









## v-text指令（类似于innerText）

>如何改变一个标签里面的内容呢？

[直通车](https://cn.vuejs.org/v2/api/#v-text)

**用法：**v-text有二种用法，里面的值都可使用一句话的表达式，如 xxx,   xxx+123   ,xxx?"1":"2"   obj.xxx等一句话简短表达式

1. ```html
   <span v-text="msg"></span>//用于整个span标签内的textContent内容替换
   ```

   

2. ```html
   <span>消息：{{msg}}</span>  //用于部分span标签内的textContent内容替换
   //这也叫插值语法
   ```
```html
   
   msg可以是一个变量，也可以是一个简短的一句话表达式
   
   

**功能：**

- v-text=“msg”  ，它会替换当前所在标签里的所有内容，并将msg内容以文本形式显示在标签里，和innerText类似

- {{}}用法，{{}}里面将不再是字符串，可包含变量（一句话内的js表达式），获取数据，它是用于标签的textContent部分，常用于部分textContent值的更新。

  下面有关于这二种用法的一个demo,大家可运行的感受一下。

​```html
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>v-text</title>
</head>

<body>
    <!-- 2:布局 -->
    <div id="app">
        <!-- 加法运算表达式 -->
        <div v-text="msg+123+'xxx'">
            今天是个寒冷的天！
            <p>xxx</p>
        </div>
        <!-- 变量用法 -->
        <div>今天天气怎么样?{{txt}}</div>
        <!-- 对象用法 -->
        <div>姓名?{{obj.name}}</div>
        <!-- 三元表达式用法 -->
        <div>性别?{{obj.name?"男":"女"}}</div>
    </div>
    <!--总结： v-text与插值语诘后面都可以用简单的一句话表达式-->

    <!-- 1：导包 -->
    <script src="./vue.js"></script>
    <!-- 3:实例化 -->
    <script>
        new Vue({
            el: "#app",
            data: {
                msg: "第一次使用v-text",
                txt: "今天是个寒冷的天！",
                obj: {
                    name: "董老师",
                    sex: "男"
                }
            }
        })
    </script>

</body>

</html>
```



## v-html指令（类似innerHTML）

>和v-text相似的一个指令，但可解析值成html

[直通车](https://cn.vuejs.org/v2/api/#v-html)

**用法：**  

```html
<div v-html="msg"></div>
```

**功能：**v-html和v-text非常相似，会替换当前所在标签的内容，并以html形式展示出来。

**注：常用于富文本（带有标签元素的字符串如：`<p style="color:red">我是v-html</p>`。）**



**v-html的demo**

```html
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>

<body>
    <!-- html布局 -->
    <div id="app">
        <div v-text="txt">123</div>
        <div v-html="txt">456</div>
    </div>
    <!-- 导包 -->
    <script src="./vue.js"></script>
    <!-- vue实例化 -->
    <script>
        new Vue({
            el: "#app",
            data: {
                txt: "<h1>今天天气很好！</h1>"    //这里就是一个富文本，带有标签的字符串
            }
        })
    </script>
</body>

</html>
```



## v-model指令

>前面学习了如何改变标签里面内容，那如何改变input框等表单元素的值呢？

[直通车](https://cn.vuejs.org/v2/guide/forms.html)

![image-20200607102420322](Vue.js - day01.assets/image-20200607102420322.png)

**用法：**例: `<input type="text" v-model="msg">`

**功能：**v-model能够实现表单元素值的双向绑定（注：适用范围为：表单元素如：input,textarea,select等）

- 双向绑定通过下面demo有一个很详情的显示，
  - 初始时，input框的值是通过v-model取到了msg的值 ，实现了值的获取 
  - 后面，当input框的值改变的过程中，也会影响到msg值的改变从而实现p标签里内容的改变
  - 所谓双向绑定就是msg与input框的值不分彼此，不管哪一个变化 ，另一个都会跟着变。

```html
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>

<body>
    <div id="app">
        <!-- 这里有二个过程来说明这个双向绑定
        1：刚开始一打开页面，msg的值通过v-model传递给了input框，让input框显示了hello,这是一个取值过程
        2：后面当input框值输入改变时，通过v-model,msg的值也会随之改变，这是一个对msg传值的过程
        取值与传值 二个过程中，v-model实现了双向绑定        
        -->
        <input type="text" placeholder="请输入内容" v-model="msg">
        <p>请输入内容：{{msg}}</p>
    </div>


    <script src="./vue.js"></script>
    <script>
        new Vue({
            el: "#app",
            data: {
                msg: "hello"
            }
        })
    </script>
</body>

</html>
```



## v-on指令

>如何监听事件呢？

[直通车](https://cn.vuejs.org/v2/guide/events.html)

**用法：(  ` v-on:=> (简写)@`)**   

```javascript
<div  v-on:事件名="需要执行的简单代码或者是方法"> </div>
//简写
 <div  @事件名="需要执行的简单代码或者是方法"> </div>
```

**功能：**用于事件的绑定，例如：`click`,`dblclick`,`mouseover ` 等只要是事件，都可用它来绑定

```html
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>

<body>
    <div id="app">
        <!-- v-on:事件名="一句话简短js" -->
        <button v-on:click="clickEvent">点我啊</button>
        <button v-on:dblclick="clickEvent">双击我啊</button>
        <button v-on:mouseover="clickEvent">鼠标移入</button>
        <p>你点了我{{total}}次!</p>


        <!-- 简写@,推荐后面都用简写 -->
        <button @click="clickEvent">点我啊</button>
        <button @dblclick="clickEvent">双击我啊</button>
        <button @mouseover="clickEvent">鼠标移入</button>

    </div>

    <script src="./vue.js"></script>
    <script>

        let app = new Vue({
            el: "#app",
            data: {
                total: 0
            },
            // 这里的methods就是方法的集合，就是我们在vue里面用到的方法都可写到这里来
            methods: {
                //在methods里访问data是需要加this的，而html访问是不需要的
                clickEvent() {   // 这里相当于clickEvent:function(){}
                    if (this.total < 10) {
                        this.total++
                    }
                }
            }
        })
    </script>
</body>
</html>
```



## vue实例中的this

>如何在methods中访问data里面的属性呢？前面使用的this代表什么呢？



**用法：**

- methods里某个方法访问data                        =>      this.data属性      
-  methods里A方法访问methods里的B方法    =>      this.B()

**功能：**this就是指当前new Vue实例，vue实例时会将data里面属性与methods方法平铺过来到vue实例里面，所以this能直接访问到data与methods

**注：**html里面访问data与methods不需要加this,但methods里面的方法访问data里的属性是需要加this的



```html
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>

<body>
    <div id="app">
        <button @click="clickEvent">点我啊</button>
        <p>{{msg}}</p>

    </div>
    <script src="./vue.js"></script>
    <script>
        //dom里面使用vue里面的data属性与methods里面的方法时都无须加this,但是vue实例里面methods访问data与methods里其它方法都是要加this的
        var app = new Vue({
            el: "#app",
            // vue中data里的值会在vue实例化时平铺到vue实例里面
            data: {
                msg: "123",
                t1: 2,
                t2: 3,
                t3: 4
            },
            //vue中里methods里面的方法也是在vue实例化时平铺到vue
            methods: {
                clickEvent() {
                    //this就代表vue实例
                    console.log(app)
                    //打印看看this里面有什么
                    console.log(this)
                    console.log(this === app);
                    this.msg = "测试一下"
                    this.alertEvent()
                },
                alertEvent() {
                    alert(this.msg)
                }

            },
        })

    </script>
</body>

</html>
```





## 搜索英雄人物demo

>对前面学的知识点综合运用



分析：

1. 处理默认打开数据
   1. input框   v-model 
   2. 姓名 ：  {{}}   heroList[heroIndex].name
   3. 英雄故事： v-text   heroList[heroIndex].story
2. 处理搜索
   1. 按钮    @click ="搜索事件"
   2. 搜索事件处理
      1. 遍历数组  heroList
      2. heroList[i].name.indexOf(inpout框的值)！=-1
      3. 存储索引   heroIndex=i
      4. data定义heroIndex=0





















这个demo是对前面所有知识点的一个综合应用。

需求：通过input框搜索出模板内的相应英雄的详信息，模板内已给出四位英雄的数据。

模板在：08搜索英雄人物demo模板.html

分析：

1. 默认取到heroList[0]的信息
   1. heroList[0].name
   2. heroList[0].story
2. 给input框来一个v-model  ="变量"
   1. 变量默认=“赛拉斯”
3. 实现搜索功能
   1. 给搜索按钮绑定事件  @click="事件"
   2. 事件要实现搜索出相应的数据
      1. 首先遍历数组
      2. 比较数组里面每一项的name
         1. name.indexOf(input框的值)！=-1
         2. 返回出相应索引 就OK了
         3. 定义一个索引变量，默认值为0









## v-on常用的几个修饰符

>如何控制事件只在某些特别条件下触发呢？

[直通车](https://cn.vuejs.org/v2/guide/events.html#%E4%BA%8B%E4%BB%B6%E4%BF%AE%E9%A5%B0%E7%AC%A6)

**用法：**

- @事件名.stop="事件执行代码"            阻止冒泡
- @事件名.prevent="事件执行代码"      阻止默认事件    
-  @keyup.enter="事件执行代码"           相当于只有按键盘回车我才触发

**功能：**.stop(阻止冒泡)   .prevent(阻止默认事件)    .enter(相当于只有按键盘回车我才触发)

```html
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<style>
    .box1 {
        width: 200px;
        height: 200px;
        background-color: #f00;
    }

    .box2 {
        width: 100px;
        height: 100px;
        background-color: #0f0;
    }
</style>

<body>
    <div id="app">
        <div class="box1" @click="box1Click">
            box1
            <!-- vue写法阻止冒泡  .stop -->
            <div class="box2" @click.stop="box2Click">box2</div>
            {{msg}}
            <!-- prevent阻止默认事件 -->
            <a href="http://www.baidu.com" @click.stop.prevent="aClickEvent">我是a标签</a>
        </div>
        <!-- 键盘监听事件修饰符.enter 只有回车时才响应 -->
        <input type="text" @keyup.enter="inputEvent">
    </div>
    <script src="./vue.js"></script>
    <script>
        new Vue({
            el: "#app",
            data: {
                msg: ""
            },
            methods: {
                box1Click() {
                    alert("这是box1")
                },
                box2Click(event) {
                    // event.stopPropagation()
                    alert("这是box2")
                },
                aClickEvent(event) {
                    // event.preventDefault()
                    this.msg = "我修改了msg"
                },
                inputEvent() {
                    alert(123)
                }

            }
        })
    </script>
</body>

</html>
```





## v-bind指令之基本用法

> 如何通过vue去改变标签上面的样式或者其它属性值呢？

常规用法官网介绍：[https://cn.vuejs.org/v2/api/#v-bind](https://cn.vuejs.org/v2/api/#v-bind)

**用法：**(  ` v-bind:=> (简写):`)

```html
v-bind:属性名="属性值" 
```

如:      v-bind:src="图片路径(这里可写一句话以内简短js)"

**功能：**绑定相应属性值后，可以动态控制该属性值，通过控制该属性值让页面满足不同的需求效果。

```html
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<style>
    img {
        width: 400px;
    }
</style>

<body>
    <div id="app">
        <button @click="clickEvent">点击我啊</button>
        <!-- 绑定基本属性：v-bind:属性名="变量" -->
        <!-- <img v-bind:src="imageSrc" :title="msg"> -->
        <!-- 简写: -->
        <img :src="imageSrc" :title="msg">
    </div>
    <script src="./vue.js"></script>
    <script>
        new Vue({
            el: "#app",
            data: {
                msg: "",
                imageSrc: "http://ossweb-img.qq.com/images/lol/web201310/skin/big517000.jpg"

            },
            methods: {
                clickEvent() {
                    this.msg = "图片换了"
                    this.imageSrc = "http://ossweb-img.qq.com/images/lol/web201310/skin/big518000.jpg"
                }
            }
        })
    </script>
</body>

</html>
```



## v-bind指令之对象用法

>如何通过vue去改变标签上面的样式或者其它属性值呢？

对象用法介绍网址： [https://cn.vuejs.org/v2/guide/class-and-style.html](https://cn.vuejs.org/v2/guide/class-and-style.html)    

**用法：**(  ` v-bind:=> (简写):`)

- 对象用法(适用于复合属性，有多个值的)  
  - 如class:      v-bind:class="{class类名：一句话以内简短js,但结果会转换为true(使用该class)与false(不使用该class),}" 

**功能：**绑定相应属性值后，可以动态控制该属性值，通过控制该属性值让页面满足不同的需求效果。

**v-bind应用于class**

```html
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<style>
    .active {
        color: red
    }
</style>

<body>
    <div id="app">
        <button @click="clickEvent">点我换颜色 </button>
        <!-- bol为true,active有效，bol为false时，active无效 -->
        <div v-bind:class="{active:bol}">使用class</div>
    </div>
    <script src="./vue.js"></script>
    <script>
        new Vue({
            el: "#app",
            data: {
                bol: false
            },
            methods: {
                clickEvent() {
                    this.bol = !this.bol
                }
            }
        })</script>
</body>

</html>
```



## 图片轮播demo

>实际运用v-bind与v-on结合

通过已给出的图片数组完成图片轮播功能，样式已给出，只需写功能就OK



## 英雄人物详细Demo

>前面所学知识点的综合运用

1. 处理默认打开数据
   1. 姓名：{{}}   hero[0].name
   2. 图片   :src     hero[0].img
   3. 故事    v-html   hero[0].story
2. 搜索
   1. input框 v-model  双向绑定  @keyup.enter="搜索事件
   2. 按钮也要来一个搜索事件  @click="搜索事件
   3. 搜索事件处理
      1. 遍历数组
      2. 找到name.indexOf(input框的值）！=-1 索引 项
      3. 存储索引  heroIndex=0



## v-for指令

>如何对列表数据进行渲染？

[直通车](https://cn.vuejs.org/v2/guide/list.html)

**用法：**   

- 用于数组 ：   `v-for="(item(数组每一项),index(索引))  in  array"`    （这里index索引也可省略不写）
- 用于对象 ：    ` v-for="(value(对象中的值)，key(对象中的键值)，index(对象中的序号，从0开始) in object)"`(这里key与index可省略,对象的for在实际项目中很少用到。)

**功能：**对数组与对象进遍历，得到每一项的值，从而进行列表之类的渲染处理。

```html
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>

<body>
    <div id="app">
        <ul>
            <!-- 数组有多长它就渲染多少个    第一个值item是代表数组当前项，第二个index值是数组索引-->
            <li v-for="(item,index) in arr">索引：{{index}}------值：{{item}}</li>
            <!-- 对对象而言，第一个值value是也是对象的值，第二个是对象的键值key，第三个index是序号 -->
            <li v-for="(value,key,index) in obj">{{value}}------{{key}}-----{{index}}</li>
        </ul>

    </div>
    <script src="./vue.js"></script>
    <script>
        new Vue({

            el: "#app",
            data: {
                arr: [1, 2, 3, 45, 6, 8, 15],
                obj: {
                    name: "刘德华",
                    age: 15
                }
            }
        })
    </script>
</body>

</html>
```





## 单纯英雄列表Demo

>v-for的一个综合应用
>

已给出相应模板，模板里有相应数据，完成列表渲染，

需求：完成列表渲染，点击不同人物名称，可实现人物样式变化 ，如图

![image-20191126212050543](image-20191126212050543.png)



1. 列表渲染   v-for 
   1. name   {{}}
2. 加入点击 事件heroIndex=index
3. 绑定class   :class="{ active :bolean值}"
   1. heroIndex==index
   2. heroIndex存储当前点击索引值





## v-if,v-else-if,v-else指令

>有条件的渲染某些内容

[直通车](https://cn.vuejs.org/v2/guide/conditional.html)

**用法：** 

-  ` v-if="一句话表达式（最后转换成boolean值，如果为真，则进行该语句所在标签渲染，如果为假则不渲染，该标签将不存在）" `  
- `v-else-if`和`v-if` 是一样用法，它是`v-if`不成立情况下才会走到`v-else-if`这里来
- `v-else`后面无须跟任何语句，当前面`v-if`和`v-if-else`都不成立时，它就会执行，当前面任何一个执行渲染，它就不执行

**功能：**根据不同条件选择性的渲染某些标签。

```html
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>

<body>
    <div id="app">
        <input type="text" placeholder="请输入相应成绩！" v-model="score">
        <div v-if="score>90">你真优秀</div>
        <div v-else-if="score>70">一般般了</div>
        <div v-else>得努力了</div>
    </div>
    <script src="./vue.js"></script>
    <script>
        new Vue({
            el: "#app",
            data: {
                score: 100
            }
        })

    </script>
</body>

</html>
```



## 补充：es6数组方法findIndex,数组与字符串判断 的includes











## Demo-完整英雄列表与查询

1. 完成英雄列表
   1. v-for对数组进行列表渲染
   2. 列表点击功能用v-on绑定事件
      1. 点击后，用个值存储当前点击项，存储它的索引就ok了
   3. v-bind绑定`class`   active   
      1.  根据索引值就可以完成class绑定
2. 完成中间详情部分
   1. 绑定英雄名称用：{{}}
   2. 图片：v-bind
   3. 英雄特征：v-for
   4. 英雄故事: v-html
3. 搜索功能
   1. input框的v-model   @keyup.enter
   2. 按钮的@click事件
      1. 搜索出input框字符相匹配的第一个项
      2. 把数组进行遍历，对里面每一项的name值进行indexOf判断 ！=-1就是匹配上的
      3. 匹配后，改变当前选中项的索引值
4. 优化，找不到相应数据的处理
   1. v-if 判断heroIndex的值是否为-1，来确定是否展示相应的数据。



 

这个demo是一个今天所学所有知识点的一个综合应用，

模板里面有相应英雄的所有数据。

一打开页面，就默认展示所有英雄列表，详情里面展示 第一个英雄的详情

![image-20191126210552823](image-20191126210552823.png)

通过搜索，搜索出带有名字带有相关字的英雄列表，同时，英雄详情展示 出列表里第一个英雄的详情。如：

![image-20191126210451600](image-20191126210451600.png)







## 今天一定需要掌握的知识点

1. vue.js是什么

   1. 它就是一个js框架，它只关心数据，无须操作dom

2. vue.js基本使用

   1. 导包

   2. 布局

   3. 实例化

      1. ~~~
         new Vue({
         el:"#app",  确定vue使用范围
         data:{//数据},
         methods:{}
         })
         ~~~

3. v-text

   1. v-text="一句话表达式"
      1. 变量   2：基本运算  3：三元表达式
   2. {{}}插值语法
      1. v-text用在标签上，全局替换
      2. {{}}用在标签文本区域，部分内容替换

4. v-html 

   1. 用于富文本解析

5. v-model

   1. 用法  v-model="变量"
   2. 使用范围：表单元素（input textarea select）

6. v-on

   1. 用法：@事件名=”一句话表达式或者function“
   2. 作用：事件绑定监听

7. this 

   1. this就是vue实例对象，在methods使用时一定要加this访问data与methods
   2. html一定不要加this

8. 修饰符

   1. .stop  阻止冒泡
   2. .prevent阻止默认事件
   3. .enter  回车事件

9. v-bind

   1. 用法:  :属性名="一句话表达式"
   2. 绑定属性控制 属性

10. v-bind对象用法

   1. :class="{class类名:boolean值}"   true :使用它所对应的class类，false:不使用它所对应的class类

11. v-for

    1. 用法:  v-for="(item,index) in 数组"
    2. item代表数组每一项   index代表索引  

12. v-if v-else-if v-else

    1. v-if="boolean值"   true :渲染所在标签  false不渲染它所在标签
    2. v-if与v-else-if   v-else一起使用时一定要是相连的兄弟标签
    3. v-else无须条件



--------------------------------------day02--------

# Day02

## 反馈

1. 语速和节奏快：**语速变慢、放慢节奏，多给大家时间吸收，提高吸收率**

2. 知识点方面：

   vue事件方法中的this指向不太清楚!   Vue的函数中，this指向的是vue实例

   v-bind 配合 @input 实现双向绑定不是很懂, 为什么要用name?

   this[e.target.name]这块不明白

   for循环中的key : key的值不要重复即可

   v-show和v-if那个用的多一点？分应用场景：如果需要频繁的切换建议使用v-show，只有当条件真正满足的时候，才渲染

   el 挂载点，#app

3. 其它建议：

   代码中多些备注

   确认一下后一天的范围

   能多停顿，让我们来理解，可能会更好！

   希望老师第二天上课时，花十几分钟把前一天讲的东西过一遍

   老师 互动起来

   关于.md笔记 希望您在记笔记的时候每个小的知识点都采用三级标题 这样我们在视图中很好定位

4. 关于基础阶段的预习视频：**因为知识点做了很大的调整和优化，跟之前班级讲的案例、知识点顺序都有很大不一样，所以暂时没有调整后的预习视频，但是项目因为变化不大，视频和资料都有，等基础阶段快结束的时候，再发给大家**

## 内容回顾

### MVVM

![image-20201013090102388](assets/image-20201013090102388.png)

### 指令

作用：替代dom操作

分类：

- {{}}：显示内容，它是写在标签之间，其它`v-`开始的写在标签的属性上，还可以进行简单的运算
- v-text & v-html：显示内容，v-text达到的效果跟`{{}}`差不多，v-html相对于`v-text`，可以解析模型中带有html标签的内容
- v-on：事件处理，事件修饰符
- v-bind：单向数据绑定，内容不是写死
- v-model：双向绑定，获取表单元素的值
- v-if & v-show：面试题：什么时候使用v-if、什么时候使用v-show
- v-for：遍历渲染，key不要忘记设置了，然后key的值要唯一
- 

**面试题：**如何使用`v-bind`+`@input`来实现`v-model`效果

1. 根据`MVVM`模型，先把架子搭起来

2. 先实现`从模型到视图`，v-bind:绑定input的value

   ![image-20201013095324644](assets/image-20201013095324644.png)

3. 再实现`从视图到模型`，@input然后把文本框中输入的值，赋值给**对应**的模型

   ![image-20201013095629641](assets/image-20201013095629641.png)

## 全局包安装失败的解决办法

1. 卸载`node`，然后去`https://nodejs.org/en/`网站上下载一个比较新的版本进行安装，再安装那两个全局包
2. 如果上一步也执行了，但是不好使，那么找到之前`npm`的缓存目录，目录一般位于`C:\Users\你的用户名\AppData\Roaming`这个文件夹下，找到`npm_cache`这个目录，把它干掉，然后再安装那两个全局包
3. 把npm的镜像设置为淘宝，在终端中执行`npm config set registry https://registry.npm.taobao.org`
4. 要保持一个好的网速，一般情况npm相关的包安装不成功，大部分情况下是网速不好的原因
5. 如果上面你都试了发现还是不行，明天到教室我再帮你看看，千万不要因为装不上而不开心，问题最终都会解决的

## 自定义指令(了解)

### 作用：

如果当你需要自定义一些指令，实现某些效果的时候，我们可以使用自定义指令

### 语法：



### 注意事项：

1. 自定义指令，分为全局和局部的，局部的自定义指令，只能在它自己本身组件中使用，如果是全局的自定义指令，那么所有组件中都可以使用，如果是全局的自定义指令是写在`new Vue`实例的外面，如果是局部的自定义指令，我们要写在`new Vue`的实例中，它也是跟`el`、`data`同级的
2. 我们在编写自定义指令的时候，我们不要加`v-`，但是在视图中使用的时候要加`v-`
3. 全局自定义指令在定义的时候，它的单词`directive`，局部的话叫做`directives`
4. 如果全局自定义指令和局部自定义指令，名字一样，那么优先调用局部

## Computed(计算属性)

### 作用:

主要是对已有的模型数据进行计算，得到一个新的结果

### 语法:

- 它是跟el、data同级，它是叫做`computed`

  ![image-20201013105627430](assets/image-20201013105627430.png)

### 注意事项：

1. 计算属性函数中必须要有返回值
2. 我们在使用计算属性函数的时候，写在`{{}}`中，但是不要加`()`
3. 计算属性一上来解析的时候，就会执行一次，然后当`依赖的`模型值发生了改变之后，他就会再次执行
4. 它有缓存，如果一个功能可以使用计算属性属性也可以使用方法实现，建议使用`计算属性`

## Watch(侦听器)

### 作用：

监听模型数据，当模型数据发生了改变，然后可以执行一些操作

### 语法：

- 和`el`、`data`同级，它的名字`watch`，它的值也是一个对象

### 注意事项：

- watch中的函数名，必须是被监听的data中的属性名
- watch对应的函数中，可以拿到新值及旧值
- watch如果是监听普通的属性，那么watch的函数名就是属性名字，但是如果我们监听的是`对象`、`数组`那么写法上会略有不同，如果是监听对象或是数组的更改，首先要写成对象的形式，并且还要设置一个属性`deep: true`

## Filter(过滤器)

### 作用：

对数据进行过滤，然后把过滤之后的结果，显示在视图上，最典型的应用场景就是对服务器返回的时间进行过滤

### 语法：

- 局部过滤器，它跟`el`、`data`同级，它的名字叫`filters`

- 如果是全局过滤器，是写在`new Vue`外面的，它叫`Vue.filter`

  ![image-20201013151733717](assets/image-20201013151733717.png)

### 注意事项：

- 过滤器也分为`全局`和`局部`，如果很多地方(页面、组件)中都要用到，我们把它定义成全局过滤器，如果只有一个地方用到，建议定义成`局部`过滤器
- 过滤器中根计算属性一样，处理完成之后，要`return`
- 如果全局的过滤器和局部的过滤器名字一样，优先调用局部

## 生命周期(钩子/函数)

### 作用：

就是在我们整个Vue实例生命过程中提供的一些函数，会在特定的时机，由Vue底层执行

### 分类：

- 初次(第一次渲染)渲染（一个生命周期之内，只会执行一次）

  - beforeCreate、created、beforeMount、mounted

- 再次(后续)渲染、可能会执行多次，前提：模型数据发生了改变

  - beforeUpdate、updated

- 销毁（一个生命周期之内，只会执行一次）

  - beforeDestory、destoryed

### 应用场景：

1. created 、mounted：发送ajax
2. mounted、updated：拿到初次渲染或是再次渲染之后dom节点
3. beforeDestory：销毁定时器

### 注意事项：

1. 这些函数不是我们自己在`methods`定义的，它是Vue底层帮我们提供的，这些函数的执行也是在特                                                                                                                        定时机，由Vue底层执行，我们只需要定义好这些函数，那么Vue就会执行了零元



## 综合案例：图书管理系统

### 要实现的效果：

图书的增删改查

###涉及到的知识点：

- 指令：v-for(图书列表渲染)、v-model(获取编号、名称的值)、v-on（提交--新增、修改）、插值表达式（图书总数）、v-if（列表数据删没有，提示一下）、v-bind（提交按钮是否可用）
- 计算属性：图书总数
- 过滤器：时间处理
- watch：监听图书重名
- 生命周期钩子：created/mounted 搞一个延时，等几秒之后给模型(图书列表)赋值
- 自定义指令：名称文本框获取焦点

### 实现步骤：

1. 渲染数组、图书总数
2. 新增
3. 修改
4. 删除
5. 其它



-----------------day03----------------
# Day03

## 反馈

建议：

- 回顾时间不要过长
- 复习一下数组（find、findIndex）和字符串的一些操作方法吗

知识点方面：

- 侦听器(watch) 和 @input 都能监听输入的值 有什么区别 过滤器 和 计算属性 功能是一样的吗

  过滤器 & 计算属性：都需要返回

  计算属性：依赖某一个模型值，算出新的结果

  过滤器：对原始数据过滤之后再显示

- 视图里指令对应视图模板里的单词不知道怎么对应，例如：视图里用v-model对应用methods:{}吗？

  v-model：data

  v-on/生命周期钩子：methods

- 监听器的应用不怎么会

- 如何实现对图书管理中两个表单元素同时监听,没思路,多个if,else之后全给禁用了

- 能不能把那个监听使用的find方法详细讲一下，对这个find函数返回值不是很清楚

其它：

- 案例敲起来真的没思路
- 能听懂 不会敲
- 知识点分散起来能够理解但是组合写一个案例一点也摸不到思路,不知道该干嘛
-  已经习惯了看视频预习第二天的内容 现在没有视频了 突然感觉不知道怎么预习了

## 综合案例（图书管理系统）

### 要实现的效果：

图书的增删改查

###涉及到的知识点：

- 指令：v-for(图书列表渲染)、v-model(获取编号、名称的值)、v-on（提交--新增、修改）、插值表达式（图书总数）、v-if（列表数据删没有，提示一下）、v-bind（提交按钮是否可用）
- 计算属性：图书总数
- 过滤器：时间处理
- watch：监听图书重名
- 生命周期钩子：created/mounted 搞一个延时，等几秒之后给模型(图书列表)赋值
- 自定义指令：名称文本框获取焦点

### 实现步骤：

1. 渲染数组、图书总数

   在`mounted`钩子中，模拟后台请求给模型数据赋值，再使用`v-for`遍历渲染

   使用`filter`对列表中的每一行时间，进行过滤

   通过`computed`实现图书总数的渲染

   使用`v-if`，当数组中的长度为0时，给它一个提示

2. 新增

   通过`v-model`获取到id、name

   给提交按钮添加点击事件

   在`submit`方法中，把id、name、date组成一个对象，push到模型books中

   当我们输入名称的时候，如果发现了名字跟已有数据相同，则禁用提交按钮（watch、v-bind）,这一步建议先把按钮的禁用或是启动先搞定，然后再监听name的输入，最后根据用户输入的值，来决定是否可用

3. 修改（难）

   给修改a标签添加点击事件（考虑a标签的默认行为），拿到id、name

   把拿到的id、name通过`v-model`显示到输入框中

   多写一个模型值`isEdit`然后根据它的值决定编号文本框是否可用（v-bind）

   点击提交之后，根据`isEdit`来进行判断，如果是`true`就是修改，如果是`false`就是新增

   如果是修改的话，先根据id找到对应的对象，然后修改对象的name和date即可

4. 删除

   给删除a标签添加点击事件（考虑a标签的默认行为），并且要拿到参数(index、id)

   根据索引，删除数组中对应的元素即可

5. 其它

   如果传递的是一个id，要删除数组中的元素，第一种根据id，找到对应的索引，然后删除

## 组件

### 传统写法（了解）

### 注意事项：

- 组件也分为全局与局部组件
- 组件中的内容，跟之前不一样的地方在于`data`必须是一个函数，并且里面要返回一个全新的`对象`

### 单文件组件(重点)

> 组成部分

- template：必须要有
- script：可选
- style：可选

### 注意事项：

- `.vue`结尾的额文件，既可以充当`页面`，也可以充当`组件`
- 页面跟组件的关系就是包含关系，比如一个页面可以包含多个组件
- 我们一般会把叫做`App.vue`的文件叫做根组件，他是第一个被看到的文件，有点首页的意思
- `.vue`结尾的文件，浏览器不识别，需要借助于`webpack`
- 运行当文件组件的时候，要把终端切换到文件所在目录，然后运行`vue serve App.vue --open`

### 脚手架 & 运行单文件组件

### 快速原型开发

这个是vue给我们提供的一个便利，这个可以让我们无需生成一个完整的项目情况下，也可以进行一些简单的尝试性

### 注意事项：

- 我们的组件，导入之后，必须注册之后才能使用

### 前提：

安装一个全局包：`npm install -g @vue/cli-service-global`

### 组件间传值

#### 父组件传值给子组件

#### 子组件传值给父组件

#### 兄弟组件传值

## 模块化

核心：模块化的核心就是导出与导入

node:  require(导入)、module.exports(导出)

**以后无论是浏览器端还是服务端(node)，都推荐使用es6的模块化导入、导出**

**导出：export default** 

**导入：import 名字 from '路径'**

--------------------day04----------------------
# Day04

## 反馈

知识点：

- 沙箱（闭包）和vue组件（模块）的相同点和不同点

  相同点：都有独立的作用域，都是解决全局变量名污染

  不同点：语法上，闭包有点缺陷，模块没有

  模块化的核心：导入和导出

  导入：import xxx from '路径'  / import  '路径'

  导出：export default(只导出一个成员) / export （导出多个成员）

- 父组件和子组件有点模糊

- 再讲下数组值传递和引用传递，数组引用类型那个没搞懂，可以讲讲原理吗？

其它：

- 一下吸收好多内容 脑容量撑不住了

## 内容回顾

页面 & 组件： 页面大，组件小

.vue组成的三部分：template(必须)、script、style

vue serve App.vue --open

## 组件传值(传数据)

### 父组件传值给子组件（props）

应用场景：以后使用element-ui的table组件，你得给它传值

传值方：父组件

​	写在template

​	![image-20201016094905925](assets/image-20201016094905925.png)

接收方：子组件

​	写在script

​	![image-20201016094955725](assets/image-20201016094955725.png)

### 子组件传值给父组件（通过触发自定义事件）

传值方：子组件

​	写在script里面

​	this.$emit(事件的名字, 参数)

![image-20201016100535814](assets/image-20201016100535814.png)

接收方：父组件

![image-20201016100626841](assets/image-20201016100626841.png)

![image-20201016100640923](assets/image-20201016100640923.png)

### 兄弟组件传值（通过触发自定义事件）

注意：虽然我们兄弟组件传值，也是使用触发自定义事件，但是跟子组件传值给父组件不一样的地方在于不能使用`this`

它需要的是一个公共的Vue实例`new Vue()`

传值方：兄弟一组件

接收方：兄弟二组件

步骤：

1、整一个模块，创建一个公共的Vue实例(bus)，然后导出

2、在传值方，导入公共的bus，然后通过调用公共的bus，触发自定义事件

![image-20201016104459183](assets/image-20201016104459183.png)

3、在接收方，在生命周期钩子`mounted`中，注册监听自定义事件，并且写好处理函数

![image-20201016105946776](assets/image-20201016105946776.png)

## 插槽（slot）(传视图)

注意：**用于父子组件之间传值****（传视图）**

体会：**灵活**

![image-20201016110900237](assets/image-20201016110900237.png)

`<slot></slot>`必须写在子组件中，它其实是起了一个占位的作用

应用场景：一般第三方UI组件中用得很多，比如ElementUI，如果我们自己要封装一个UI组件库，也离不开插槽

一般第三方UI组件库中的那些组件，其实里面就写好了`<slot></slot>`，等着我们使用他们的时候去替换他们，比如

`Dialog`

### 默认插槽

步骤：

1. 形成好父子组件关系

2. 在子组件中使用`<slot>默认内容</slot>`默认插槽占个位

3. 在父组件template中，在使用子组件的标签之间写上我们的**视图部分**

   ```vue
   <template>
   	<my-dialog>
       	想要显示的内容，写在这里
       </my-dialog>
   </template>
   ```

### 具名插槽

应用场景：一个子组件中有多个地方要被替换，这个时候，我们就使用具名插槽

步骤：

1. 形成好父子组件关系

2. 在子组件中，使用多个`<slot></slot>`写好插槽，但是一个子组件中只能有一个默认插槽

   ![image-20201016121103180](assets/image-20201016121103180.png)

3. 在父组件中，根据插槽的名字来进行替换，也需要在template中的**子组件标签**之间写

   ![image-20201016121154804](assets/image-20201016121154804.png)

   

### 作用域插槽（难）

作用：把显示内容的控制权，控制在父组件中

涉及到的知识点：父组件传值给子组件、

步骤：

1. 形成父子关系

2. 在父组件中给MyTable子组件，传递一个数组

   ![image-20201016154953267](assets/image-20201016154953267.png)

3. 子组件接收到值之后，把能渲染的，先渲染

   ![image-20201016155027971](assets/image-20201016155027971.png)![image-20201016155039640](assets/image-20201016155039640.png)

4. 子组件中，最后一列无法自己搞定，那么它就使用`<slot v-bind:xxx="yyy"></slot>`把值传递给父组件，并且等着将来被替换

   ![image-20201016155248194](assets/image-20201016155248194.png)

5. 父组通过作用域插槽` <template #test="slotProps"></template>`，拿到子组件中传递的值，然后渲染好好内容，最终把子组件中`<slot></slot>`占位的地方替换到

   ![image-20201016155332362](assets/image-20201016155332362.png)

疑惑点：

1. 为什么现在自己写子组件，后面我们就不会再写子组件了，因为Element已经写好了，它内部就是通过`<slot v-bind:xxx="yyy"></slot>`传给父组件
2. 我们以后主要是在父组件中，通过作用域插槽` <template #test="slotProps"></template>`，拿嗯么到子组件传递过来的值，然后自己决定渲染成什么样子（**父组件掌握控制权**）

## 路由

### 概念

它是用来实现单页面应用(SPA Single Page Application)，与之对应的是多页应用（京东、淘宝）

单页面应用的典型例子：网易云音乐

多页面应用的典型例子：京东

### 使用脚手架生成项目

前提：安装全局包 `npm i @vue/cli -g`

生成步骤：

1. 把终端切换到桌面

2. 在终端中输入`vue create router_test`

3. 进行选择

   ![image-20201016160308118](assets/image-20201016160308118.png)

   ![image-20201016160427645](assets/image-20201016160427645.png)

   ![image-20201016160441536](assets/image-20201016160441536.png)

   ![image-20201016160508918](assets/image-20201016160508918.png)

   ![image-20201016160542189](assets/image-20201016160542189.png)

   ![image-20201016160628189](assets/image-20201016160628189.png)

   ![image-20201016160751437](assets/image-20201016160751437.png)

4. 把**项目切换到生成项目的根目录下**，运行`npm run serve`

   ![image-20201016161432242](assets/image-20201016161432242.png)

   5. 如果希望运行`npm run serve`的时候，自动打开浏览器，在项目根目录下的package.json中的scripts/serve 的值后面

      接一个--open

      ![image-20201016161644788](assets/image-20201016161644788.png)

      6. 当项目生成完之后，运行也没有问题了，那么就在终端中输入`npm i vue-router`【注意：在项目根目录】

### 基本使用


--------------------day05 -----------------------
# 反馈

建议：

- 老师能否把知识点放到单个文件里头讲?
- 注释写的再详细一点
- 录制视频音质，能不能调整下
- 讲快点,尽量多给点时间敲.比如坤哥,上午讲完下午敲!真香.
- 回顾的时候把重点回顾就可以了
- 希望可以回顾下语法 就只写语法的那种 这一步写完下一步去那里写 不要带入案例的说一下

知识点：

- 子传父$emit()只能写在事件中吗
- 组件可以改数据和视图，插槽只能改视图是吗？
- this.$emit的this是啥, 父组通过拿到子组件中传递的值，然后渲染好好内容，把子组件中<slot></slot>占位的地方替换到以后,这个会影响复用这个子组件吗还是只针对这一次
- 子组件传值给父组件，父组件接收子组件在子组件上监听事件@子组件触发事件名=“事件处理函数（这里需要写形参接收传过来的参数吗？）”，因为在methods里面有写形参，前后不一致啊好像
- 插槽传值 ：和v-bind的简写：怎么区分
- 兄弟间传值用生命周期钩子mounted，这个不太懂，老师能再讲讲吗

其它:

- 网太卡了 不好查资料

## 内容回顾

组件间传值：

- 父组件传值给子组件（props）
- 子组件传值给父组件（触发自定义事件）
- 兄弟组件传值（使用公共的bus，触发自定义事件）

插槽：只作用于父子组件之间

- 默认插槽
- 具名插槽
- 作用域插槽：把控制显示的权利掌握在父组件中

路由：实现单页面应用

生成项目：按照我截图去走

## 路由

### 基本使用

效果：点击两个链接的时候，分别显示对应的内容，并且浏览器不刷新，也不打开新的页面

实现步骤1：(按照之前的知识点想的步骤)

1. 创建两个组件(NewsList.vue、FoodList.vue)，并且写好内容
2. 在父组件(App.vue)中导入两个子组件(NewsList.vue、FoodList.vue)，并且注册，使用
3. 在父组件(App.vue)中设置两个a标签，给他们添加点击事件
4. 当点击新闻列表的时候，通过条件渲染显示新闻列表
5. 当点击食品列表的时候，通过条件渲染显示食品列表

实现步骤1：(使用vue-router实现)

1. 创建两个组件(NewsList.vue、FoodList.vue)，并且写好内容

2. 在`App.vue`中，使用`<router-link to="/newslist"></router-link>`

3. 安装路由包`npm i vue-router`

4. 在src目录下创建一个`router`文件夹，里面再创建一个`index.js`的文件，然后写好路由的代码

   ```js
   /**
    * 路由相关的代码就写在这里
    */
   // 导包
   import Vue from 'vue'
   import VueRouter from 'vue-router'
   
   // 在项目中使用路由
   Vue.use(VueRouter)
   
   // 创建路由对象，并且导出
   const router = new VueRouter({
     // 配置路由规则
     routes: []
   })
   
   // 导出
   export default router
   ```

5. 在`main.js`中导入创建好的路由对象，并且注入到根实例中，这样我们整个项目就有了路由功能

   ```js
   // 导入路由
   import router from './router'
   
   new Vue({
     render: h => h(App), //h其实是形参
     router // 把路由注入到根实例中、让我们应用拥有路由的功能
   }).$mount('#app')
   ```

6. 在`src/router/index.js`中在创建路由对象的时候，设置好里面的路由规则`routes`

   ```js
   // 创建路由对象，并且导出
   const router = new VueRouter({
     // routes: 配置路由规则,这个`routes`很多人容易写错
     routes: [
       /**
        * path：匹配的路径
        * component: 匹配到的组件,这个单词不要写错
        */
       { path: '/newslist', component: NewsList },
       { path: '/foodlist', component: FoodList }
     ]
   })
   ```

7. 我们得在`App.vue`中某个位置，写上路由出口`<router-view></router-view>`，然后路由匹配到的组件将渲染在这里

   ```vue
   <template>
     <div>
       <p>
         <!-- 1.0 设置点击链接 -->
         <!-- router-link最终会渲染成a标签，to代表跳转到地址，必须设置，最终会渲染成href，否则报错 -->
   
         <!-- 当我们配置完我们路由代码之后，这个组件就自动起作用了，原因是因为，我们写完路由代码之后
         这个router-link组件会进行全局注册 -->
         <router-link to="/newslist">新闻列表</router-link>&nbsp;
         <router-link to="/foodlist">食品列表</router-link>
       </p>
       <!-- 路由出口 -->
       <!-- 路由匹配到的组件将渲染在这里 -->
       <router-view></router-view>
     </div>
   </template>
   ```

### 路由模式

> hash模式

页面的url上面会有一个`#`，这个叫做hash，这个也是路由的默认模式，hash无需后台配置

> history模式

页面的url上面没有`#`，它底层实现是基于h5之后history的一个新API，`history.pushState`，但是如果我们使用history模式的话，我们的后台得配合我们

设置：在`src/router/index.js`中进行设置

```js
// 创建路由对象，并且导出
const router = new VueRouter({
  mode: 'history', // 默认是hash，如果你要更改，那么就在mode属性中更改
  // 5. routes: 配置路由规则,这个`routes`很多人容易写错
  routes: [
    /**
     * path：匹配的路径
     * component: 匹配到的组件,这个单词不要写错
     */
    { path: '/newslist', component: NewsList },
    { path: '/foodlist', component: FoodList }
  ]
})
```

### 命名路由、重定向、404

![image-20201017105832869](assets/image-20201017105832869.png)

### 路由传参

> query

语法：比如：/playlist?id=5089855855

实现步骤：

1. 先创建一个`NewsDetail.vue`

2. 在`NewsList.vue`中使用`<router-link></router-link>`设置跳转链接

   ```vue
   <template>
     <div>
       <ul>
         <li>
           <router-link to="/newsdetail?id=1001">上海名媛拼多多</router-link>
         </li>
         <li>
           <router-link to="/newsdetail?id=1002">新冠疫苗上市了</router-link>
         </li>
         <li>
           <router-link to="/newsdetail?id=1003">深圳成立40周年</router-link>
         </li>
       </ul>
     </div>
   </template>
   ```

3. 在`src/router/index.js`中设置路由规则

   ```js
   // query传参，路由规则设置
   { path: '/newsdetail', component: NewsDetail }
   ```

4. 在跳转过来的组件中，拿到id

   ![image-20201017112037973](assets/image-20201017112037973.png)

> params

语法：比如：/mv/10929636

步骤：

1. 新建一个`FoodDetail.vue`

2. 在`FoodList.vue`中通过`router-link`，设置可以点击的链接

   ```vue
   <template>
     <div>
       <ul>
         <li>
           <router-link to="/fooddetail/2001">法国鹅肝</router-link>
         </li>
         <li>
           <router-link to="/fooddetail/2002">澳洲鱼子酱</router-link>
         </li>
         <li>
           <router-link to="/fooddetail/2003">澳洲波龙</router-link>
         </li>
       </ul>
     </div>
   </template>
   ```

3. 在`src/router/index.js`中配置路由规则**(不一样)**

  
   ![image-20201017115152131](assets/image-20201017115152131.png)

4. 在新的组件中，获取参数

   ![image-20201017115503190](assets/image-20201017115503190.png)



### 嵌套路由 & 编程式导航

嵌套路由：应用场景，后台管理

`$route`、`$router`

$route：获取参数(query,params)、监听路由变化

`$router`：编程式导航  `this.$router.push('/layout')`，就是通过js代码跳转

实现步骤：（主要写Layout中的代码实现）

1. 把左边、右边UI写好

2. 创建两个组件`Menu1.vue`、`Menu2.vue`【它只是展示在Layout.vue的右边】

3. 在Layout.vue左边，使用`<router-link to="xxx"></router-link>`设置好跳转链接

4. 在`src/router/index.js`我们要配置嵌套路由规则了，**这个和之前还略有不同**

   ![image-20201017152112537](assets/image-20201017152112537.png)

5. 在`Layout.vue`的右边，设置下路由出口

   ![image-20201017152152826](assets/image-20201017152152826.png)

## axios

### 作用：

用于发送ajax请求

文档地址：https://github.com/Duanzihuang/vuebase/blob/main/day05/2-%E5%85%B6%E5%AE%83%E8%B5%84%E6%96%99/server_api/API.md

方法名的含义：GET：获取、POST：新增、PUT：修改、DELETE：删除

### 注意：

注意：

- **`GET`和`DELETE`都是是通过url传参，建议写在第二个参数上，通过`params: {aaa:bbb,yyy:zzz}`传参**
- **`POST`和`PUT`都是通过请求体传参，也是写在第二个参数上，但是不需要设置`params`属性，直接写`{aaa:bbb,yyy:zzz}`即可，这个要和`GET`、`DELETE`区别一下**
- **如果需要设置请求头，一定要注意，如果是`GET`和`DELETE`请求，则是要写在第二个参数上，和`params`同级，例如`axios.get(url, {params:{aaa:bbb,yyy:zzz},headers: {token:'Dadsafd111'}})`如果是`POST`和`PUT`则写在第三个参数上，例如`axios.post(url, data,{headers: {token:'Dadsfsa222'}})`，强烈建议如果多个地方都需要设置请求头，则统一在请求拦截器中进行处理**

### 基本使用

1. 导入axios
2. axios.get、axios.post、axios.put、axios.delete

### 其它配置

基准路径：axios.defaults.baseURL = 'http://huangjiangjun.top:3006/api/'

### 拦截器

建议：不要手写，容易单词写错，copy过来之后再改

```js
// 设置请求拦截器
axios.interceptors.request.use(
  function (config) {
    // 在发送请求之前做些什么
    // console.log(config)
    config.headers.token = 'asfasfasdaf'
    return config
  },
  function (error) {
    // 对请求错误做些什么
    return Promise.reject(error)
  }
)

// 添加响应拦截器
axios.interceptors.response.use(
  function (response) {
    // 对响应数据做点什么
    // console.log(response)
    // 只把服务器返回的数据返回给 axios.get/post/put/delete的方法中的then
    return response.data
  },
  function (error) {
    // 对响应错误做点什么
    return Promise.reject(error)
  }
)
```

## async(异步函数)

### 回顾Promise

![image-20201017171002768](assets/image-20201017171002768.png)

## 其它

Vue中哪些地方建议大写，哪些地方需要小写

大写：组件的命名，及导入它时候的名字

小写：组件的 template 中、路由规则中的 path 也是小写

### 作业

在桌面生成一个叫做`hymusic`的项目

生成项目之后再安装如下包：

npm i vue-router axios element-ui moment

**安装之后，把终端切换到项目根目录，然后运行**`npm run serve`


----------------day06------------------



# Day06

## 反馈

建议：

- 建议老师多多用画图来解释一些不好理解的内容；讲解多步骤的操作时，尽量在中间给我们一段理解的时间；下课时间放点音乐也是不错的！
- 希望老师把重点和非重点（仅了解即可）的内容分开讲
- 希望知识点讲得更言简意赅些

知识点：

- put请求 和 delete请求 有什么特点 他拥有get的请求的特点 还是post请求的特点

  put请求是向服务器发送数据的，从而改变信息，修改数据的内容

  他拥有post请求的 特点 post能添加内容 而put只能修改内容

- 配置路由 从头到尾以后是不是都是我们整?

  

- query和params 只用其中一个不会有问题吧？

  

- name可以和params、query两个一起使用；

-  path只能和query使用；

-  使用params传参刷新后不会保存，使用query传参刷新可以保存；

-  params不会再地址栏显示，query会在地址栏显示；

-  params能和动态路由一起使用，而query不能

其它：

- **虽然知识点多，晚上一总结后基本清晰明朗，多多熟悉流程就好**

## 内容回顾

路由

axios

async

## 黑云音乐

### 项目模块及用到的知识点分析

模块 & 知识点：

- App.vue：左边菜单、右边的 router-view、下边的音频播放子组件
- 发现音乐：轮播图(element-ui)、v-for
- 推荐歌单：v-bind、插值表达式、v-for、watch
- 歌单详情：路由传参(query)、v-bind、插值表达式、v-for、全局过滤器
- 最新音乐：watch、v-for
- 最新MV：watch、v-for、全局过滤器
- MV详情：video、v-bind、插值表达式、全局过滤器、v-for、watch
- 音频播放组件：v-bind、bus传值
- 其它：axios(挂载到Vue原型)

### 项目实现思路分析

1. 生成项目

2. 把项目中的**路由**配置好

   - 创建必要的一些页面（发现音乐、推荐歌单、最新音乐、最新MV）

   - 在`src`创建一个`router`里面写一个`index.js`,写上路由代码（创建路由对象、配置规则、导出）

     ```js
     // 导包
     import Vue from 'vue'
     import VueRouter from 'vue-router'
     
     // 在Vue中使用路由
     Vue.use(VueRouter)
     
     // 导入页面
     // 发现音乐
     import Discovery from '@/views/Discovery'
     // 推荐歌单
     import Playlists from '@/views/Playlists'
     // 最新音乐
     import Songs from '@/views/Songs'
     // 最新MV
     import Mvs from '@/views/Mvs'
     // 404
     import NotFound from '@/views/NotFound'
     
     // 创建路由对象
     const router = new VueRouter({
       // 这个地方的配置，就是我们需要写的
       // routes 这个单词就叫这个，不要写错
       routes: [
         { path: '/discovery', component: Discovery },
         { path: '/', redirect: '/discovery' }, // 重定向
         { path: '/playlists', component: Playlists },
         { path: '/songs', component: Songs },
         { path: '/mvs', component: Mvs },
         { path: '*', component: NotFound }
       ]
     })
     
     // 导出
     export default router
     ```

   - 在`main.js`中导入路由对象，并且注入到根实例中

     ```js
     import Vue from 'vue'
     import App from './App.vue'
     
     Vue.config.productionTip = false
     
     // 导入路由
     import router from './router'
     
     new Vue({
       render: h => h(App),
       router // 注入到根实例中，让我们整个应用拥有路由的功能
     }).$mount('#app')
     ```

3. 在`App.vue`中写代码

   - 实现左边的导航菜单（`router-link`）

   - 实现右边的路由出口（`router-view`）

   - 实现下边的音频子组件显示(`MyAudio.vue`)

     ```vue
     <template>
       <div class="index-container">
         <!-- 左边 -->
         <div class="nav">
           <ul>
             <li>
               <!-- 当我们router-link选中的时候，会自动给我们添加router-link-exact-active router-link-active -->
               <router-link to="/discovery">
                 <span class="iconfont icon-find-music">&nbsp;发现音乐</span>
               </router-link>
             </li>
             <li>
               <router-link to="/playlists">
                 <span class="iconfont icon-music-list">&nbsp;推荐歌单</span>
               </router-link>
             </li>
             <li>
               <router-link to="/songs">
                 <span class="iconfont icon-music">&nbsp;最新音乐</span>
               </router-link>
             </li>
             <li>
               <router-link to="/mvs">
                 <span class="iconfont icon-mv">&nbsp;最新MV</span>
               </router-link>
             </li>
           </ul>
         </div>
         <!-- 右边 -->
         <div class="main">
           <!-- 路由出口 -->
           <router-view></router-view>
         </div>
         <!-- 下边 -->
         <my-audio />
       </div>
     </template>
     
     <script>
     // 导入MyAudio子组件
     import MyAudio from '@/components/MyAudio'
     export default {
       components: {
         MyAudio
       }
     }
     </script>
     
     <style lang="less" scoped>
     // scoped 代表样式私有（vue解析的时候，给我们添加了一些自定义的属性）
     // 应用场景：当我们很多个页面中都有同一个名字的样式的时候，我们就需要加上 scoped
     // scoped 有一个特定，只能作用域我们自己写的代码，如果你使用了element-ui等第三方的，它不会作用到
     // 第三方的ui组件中
     // 注意：不是说所有情况下，我们都需要加上scoped，我们的类名跟其它页面中冲突了，我们才需要加，并且使用
     // 了第三方UI组件的时候，我们也不加，比如 发现音乐
     .index-container {
       height: 100%;
       display: flex;
       .nav {
         background-color: #ededed;
         width: 200px;
         height: 100%;
         li {
           height: 60px;
           cursor: pointer;
           display: flex;
           align-items: center;
           &:hover {
             background-color: #e7e7e7;
           }
           .iconfont {
             margin-right: 10px;
             font-size: 20px;
           }
           a {
             color: black;
             padding-left: 30px;
             font-size: 18px;
             line-height: 60px;
             width: 100%;
             height: 100%;
             &.router-link-active {
               color: #dd6d60;
               .iconfont {
                 color: #dd6d60;
               }
             }
           }
         }
       }
       .main {
         flex: 1;
         overflow-y: scroll;
         padding: 10px 20px;
         padding-bottom: 60px;
         > div {
           max-width: 1100px;
           margin: 0 auto;
         }
       }
     }
     </style>
     ```

4. 在写`App.vue`的过程中，发现样式不符合要求，设置一下全局样式，在`src`目录下创建一个`styles/global.less`，并且还需要在`main.js`中去导入(为了让webpack打包的时候，知道全局样式的存在)

   ```less
   body,
   html {
     height: 100%;
     margin: 0;
     padding: 0;
     font-weight: 400;
     font-family: Helvetica Neue, Helvetica, PingFang SC, Hiragino Sans GB,
       Microsoft YaHei, '\5FAE\8F6F\96C5\9ED1', Arial, sans-serif;
   }
   * {
     box-sizing: border-box;
     margin: 0;
     padding: 0;
     transition: 0.5s;
   }
   a {
     text-decoration: none;
     color: #595959;
   }
   ul,
   ol {
     list-style: none;
   }
   .iconfont {
     cursor: pointer;
   }
   .el-loading-spinner{
     .el-loading-text,.el-icon-loading{
       color:#dd6d60
     }
   }
   .el-table {
     border-bottom: none;
     border-collapse: collapse;
     &.playlit-table {
       th:nth-child(2) {
         width: 130px;
       }
       th:nth-child(3) {
         width: 300px;
       }
       th:nth-child(4) {
         width: 200px;
       }
     }
     tbody {
       border-bottom: none;
     }
     td {
       border-bottom: none;
       &:first-child {
         padding-left: 10px;
       }
     }
     th {
       font-weight: normal;
       &:first-child {
         width: 50px;
       }
       &:nth-child(2) {
         width: 300px;
       }
       &:nth-child(3) {
         width: 200px;
       }
     }
     .song-wrap {
       > span {
         margin-top: 20px;
         display: inline-block;
         color: #bebebe;
       }
       .icon-mv {
         padding-left: 5px;
         color: #dd6d60;
       }
     }
     .img-wrap {
       position: relative;
       width: 70px;
       height: 70px;
       img {
         width: 70px;
         height: 70px;
         border-radius: 5px;
       }
       .icon-play {
         position: absolute;
         top: 50%;
         left: 50%;
         transform: translate(-50%, -50%);
         width: 25px;
         height: 25px;
         color: #dd6d60;
         font-size: 12px;
         border-radius: 50%;
         display: flex;
         align-items: center;
         justify-content: center;
         background: rgba(255, 255, 255, 0.8);
         &::before {
           transform: translateX(1px);
         }
       }
     }
     tr {
       &:nth-child(2n) {
         background-color: #fafafa;
       }
       &:hover {
         background-color: #f5f7fa;
       }
     }
   }
   ```

5. 我们项目中用到的字体图标不是通过本地加载的，我们是直接加载的线上地址，我们在`public/index.html`中，通过link标签引入

   ```html
   <!DOCTYPE html>
   <html lang="en">
     <head>
       <meta charset="utf-8" />
       <meta http-equiv="X-UA-Compatible" content="IE=edge" />
       <meta name="viewport" content="width=device-width,initial-scale=1.0" />
       <link rel="icon" href="<%= BASE_URL %>favicon.ico" />
       <title><%= htmlWebpackPlugin.options.title %></title>
       <!-- 导入 iconfont的字体图标 -->
       <link
         rel="stylesheet"
         href="//at.alicdn.com/t/font_1654044_7fb6pz0mo1.css"
       />
     </head>
     <body>
       <noscript>
         <strong
           >We're sorry but <%= htmlWebpackPlugin.options.title %> doesn't work
           properly without JavaScript enabled. Please enable it to
           continue.</strong
         >
       </noscript>
       <div id="app"></div>
       <!-- built files will be auto injected -->
     </body>
   </html>
   ```

6. 全局axios的配置（npm i axios），也写在`main.js`中

   - 基准路径

   - 拦截器：请求拦截器（加载提示）、响应拦截器（返回需要的data）

     ```js
     // 配置axios
     import axios from 'axios'
     // 设置基准路径
     axios.defaults.baseURL = 'http://huangjiangjun.top:9000/'
     // 设置拦截器
     // 添加请求拦截器
     axios.interceptors.request.use(
       function (config) {
         // 在发送请求之前做些什么
         return config
       },
       function (error) {
         // 对请求错误做些什么
         return Promise.reject(error)
       }
     )
     
     // 添加响应拦截器
     axios.interceptors.response.use(
       function (response) {
         // 对响应数据做点什么
         return response.data
       },
       function (error) {
         // 对响应错误做点什么
         return Promise.reject(error)
       }
     )
     // 把axios挂载到Vue的原型上
     // 因为Vue实例中，大部分属性都已`$`开头，所以我们写得时候，也按照这个预定
     Vue.prototype.$http = axios
     ```

7. 发现音乐、推荐歌单、歌单详情、最新音乐、最新MV、MV详情各自实现

### 生成项目

使用`@vue/cli`生成项目，具体详见第四天的笔记

### 所依赖的包

- vue：核心包
- vue-router：路由
- axios：网络请求
- element-ui：UI组件库
- moment：时间处理
- less：解析less

## 发现音乐

### 获取轮播图数据

```vue
<script>
export default {
  name: 'Discovery',
  data () {
    return {
      bannerList: [] // 轮播图的列表
    }
  },
  mounted () {
    // 获取轮播图数据
    this.getBannerListData()
  },
  methods: {
    async getBannerListData () {
      const res = await this.$http.get('banner')

      this.bannerList = res.banners
    }
  }
}
</script>
```

### 渲染轮播图

```vue
<template>
  <div class="discovery-container">
    <!-- 1.0 渲染轮播图 -->
    <el-carousel type="card" :interval="3000" arrow="always">
      <el-carousel-item v-for="item in bannerList" :key="item.imageUrl">
        <img :src="item.imageUrl" alt="" />
      </el-carousel-item>
    </el-carousel>
  </div>
</template>
```

### 获取推荐歌单、最新音乐、最新MV数据

```vue
<script>
export default {
  name: 'Discovery',
  data () {
    return {
      bannerList: [], // 轮播图的列表
      // 推荐歌单
      recommendList: [],
      // 最新音乐
      songList: [],
      // 推荐MV
      mvList: []
    }
  },
  mounted () {
    // 获取轮播图数据
    this.getBannerListData()
    // 获取推荐歌单的数据
    this.getRecommendListData()
    // 获取最新音乐数据
    this.getSongListData()
    // 获取最新MV
    this.getMvListData()
  },
  methods: {
    async getBannerListData () {
      const res = await this.$http.get('banner')

      this.bannerList = res.banners
    },
    async getRecommendListData () {
      const res = await this.$http.get('personalized', {
        params: {
          limit: 10
        }
      })

      this.recommendList = res.result
    },
    async getSongListData () {
      const res = await this.$http.get('personalized/newsong')

      this.songList = res.result
    },
    async getMvListData () {
      const res = await this.$http.get('personalized/mv')

      this.mvList = res.result
    }
  }
}
</script>
```

> 渲染推荐歌单、最新音乐、最新MV

```vue
<template>
	<!-- 2.0 渲染推荐歌单 -->
    <div class="recommend">
      <h3 class="title">推荐歌单</h3>
      <div class="items">
        <div class="item" v-for="item in recommendList" :key="item.id">
          <div class="img-wrap">
            <div class="desc-wrap">
              <span class="desc">{{ item.copywriter }}</span>
            </div>
            <img :src="item.picUrl" alt="" />
            <span class="iconfont icon-play"></span>
          </div>
          <p class="name">{{ item.name }}</p>
        </div>
      </div>
    </div>
    <!-- 3.0 渲染最新音乐 -->
    <div class="news">
      <h3 class="title">最新音乐</h3>
      <div class="items">
        <div class="item" v-for="item in songList" :key="item.id">
          <div class="img-wrap">
            <img :src="item.picUrl" alt="" />
            <span class="iconfont icon-play"></span>
          </div>
          <div class="song-wrap">
            <div class="song-name">{{ item.name }}</div>
            <div class="singer">{{ item.song.artists[0].name }}</div>
          </div>
        </div>
      </div>
    </div>
    <!-- 渲染最新MV -->
    <div class="mvs">
      <h3 class="title">推荐MV</h3>
      <div class="items">
        <div class="item" v-for="item in mvList" :key="item.id">
          <div class="img-wrap">
            <img :src="item.picUrl" alt="" /><span
              class="iconfont icon-play"
            ></span>
            <div class="num-wrap">
              <div class="iconfont icon-play"></div>
              <div class="num">{{ item.playCount | formatCount }}</div>
            </div>
          </div>
          <div class="info-wrap">
            <div class="name">{{ item.name }}</div>
            <div class="singer">{{ item.artists[0].name }}</div>
          </div>
        </div>
      </div>
    </div>
</template>
```

> 对播放次数进行过滤处理

条件：当我们的次数大于10，我们就显示多少万，并且我们现实次数可能其他地方也需要用到，所以我们把他们定义成全局过滤器，写在`main.js`中

```js
// 全局过滤器
Vue.filter('formatCount', val => {
  if (val / 10000 > 10) {
    return parseInt(val / 10000) + '万'
  } else {
    return val
  }
})
```

## 推荐歌单

### 获取数据

```vue
<script>
export default {
  name: 'Playlists',
  data () {
    return {
      // 顶部的标题
      topName: '',
      // 顶部的描述
      topDesc: '',
      // 顶部的封面
      topCover: '',
      // 选中分类
      cat: '全部',
      // 歌单所有的分类
      cats: [
        '全部',
        '欧美',
        '华语',
        '流行',
        '说唱',
        '摇滚',
        '民谣',
        '电子',
        '轻音乐',
        '影视原声',
        'ACG',
        '怀旧',
        '治愈',
        '旅行'
      ],
      // 精品歌单列表
      topPlayLists: []
    }
  },
  mounted () {
    // 获取顶部精品歌单的数据
    this.getHighqualityData()
    // 获取精品歌单列表
    this.getTopPlayListsData()
  },
  watch: {
    cat () {
      // 当分类发生改变之后，重新去根据新的分类，加载新的数据
      this.getTopPlayListsData()
    }
  },
  methods: {
    async getHighqualityData () {
      const res = await this.$http.get('top/playlist/highquality?limit=1')

      this.topName = res.playlists[0].name
      this.topDesc = res.playlists[0].description
      this.topCover = res.playlists[0].coverImgUrl
    },
    async getTopPlayListsData () {
      const res = await this.$http.get('top/playlist', {
        params: {
          offset: 0,
          limit: 10,
          cat: this.cat
        }
      })

      this.topPlayLists = res.playlists
    }
  }
}
</script>
```

### 渲染

```vue
<template>
  <div class="playlists-container">
    <!-- 渲染精品歌单 -->
    <div class="top-card">
      <div class="icon-wrap">
        <img :src="topCover" alt="" />
      </div>
      <div class="content-wrap">
        <div class="tag">精品歌单</div>
        <div class="title">{{ topName }}</div>
        <div class="info">
          {{ topDesc }}
        </div>
      </div>
      <img :src="topCover" alt="" class="bg" />
      <div class="bg-mask"></div>
    </div>
    <!-- 渲染歌单分类及歌曲列表 -->
    <div class="tab-container">
      <!-- 分类 -->
      <div class="tab-bar">
        <span
          v-for="item in cats"
          :key="item"
          :class="['item', item === cat ? 'active' : '']"
          @click="cat = item"
          >{{ item }}</span
        >
      </div>
      <!-- 歌曲列表 -->
      <div class="tab-content">
        <div class="items">
          <div class="item" v-for="item in topPlayLists" :key="item.id">
            <div class="img-wrap">
              <div class="num-wrap">
                播放量:
                <span class="num">{{ item.playCount | formatCount }}</span>
              </div>
              <img :src="item.coverImgUrl" alt="" /><span
                class="iconfont icon-play"
              ></span>
            </div>
            <p class="name">
              {{ item.description }}
            </p>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
```

### 监听 cat 改变，发请求，重新渲染

```vue
<template>
	<div>
        <span
          v-for="item in cats"
          :key="item"
          :class="['item', item === cat ? 'active' : '']"
          @click="cat = item"
          >{{ item }}</span
        >
    </div>
</template>

<script>
	export default {
        watch: {
            cat () {
              // 当分类发生改变之后，重新去根据新的分类，加载新的数据
              this.getTopPlayListsData()
            }
        }
    }
</script>
```

----------------------------day07 -----------------


#    Day07

## 反馈

- 写代码一小时,半个小时在找bug,20分钟在装包,怎么办

- 希望老师可以多给一些预习资料，不够看

- 讲到下午四点半老师还在补充各种注意点，听无法专心写代码，不听又怕遗漏什么，也没有预习资料视频什么的，就晚上有点时间自行补漏，时间远远不够，晚上熬夜看视频补习，第二天上午教室倒一大片。。。希望老师把小知识点要下载安装的各种包在下午关闭公屏之前花点时间讲解完，不然我们基础差的同学50%的内容都吸收不了，老师辛辛苦苦讲一天也是付之东流

- 听起来挺简单的 , 敲的时间太紧有点炸心态 . 不能天天熬夜写代码吧 ?

  html、css

## 回顾

## 歌单详情

步骤：

1. 创建一个`Playlist.vue`，并且写好基本内容

2. 在`src/router/index.js`中配置路由规则

   ```js
   // query 方式传参
   { path: '/playlist', component: Playlist }
   ```

3. 在`发现音乐`、`歌单列表`中，通过编程式导航跳转(`this.$router.push`)

   ```vue
   <script>
   	export default {
           ...
           methods: {
               // 跳转到歌单详情
               toPlaylist(id) {
                 this.$router.push(`/playlist?id=${id}`)
               }
           }
       }
   </script>
   ```

4. 拿到歌单id、发送请求

   ```vue
   <script>
   export default {
     name: 'Playlist',
     data () {
       return {
         playlist: {} // 初次渲染
       }
     },
     // Vue底层会自动执行这个生命周期钩子，只会执行一次
     // https://cn.vuejs.org/v2/guide/instance.html
     mounted () {
       this.getPlaylistData()
     },
     methods: {
       async getPlaylistData () {
         const res = await this.$http.get(
           `playlist/detail?id=${this.$route.query.id}`
         )
   
         // 再次渲染
         this.playlist = res.playlist
       }
     }
   }
   </script>
   ```

5. 渲染(时长、时间的过滤器)，这是全局的，所以写在`main.js`

   ```js
   // 格式化播放次数
   Vue.filter('formatCount', val => {
     if (val / 10000 > 10) {
       return parseInt(val / 10000) + '万'
     } else {
       return val
     }
   })
   // 格式化时间
   import moment from 'moment'
   Vue.filter('formatTime', (val, formatStr = 'YYYY-MM-DD HH:mm:ss') => {
     return moment(val).format(formatStr)
   })
   // 格式化分秒
   Vue.filter('formatSecond', val => {
     // 处理分
     let min = Math.ceil(val / 1000 / 60)
     min = min < 10 ? '0' + min : min
   
     // 处理秒
     let second = Math.ceil((val / 1000) % 60)
     second = second < 10 ? '0' + second : second
   
     return min + ':' + second
   })
   ```

   

##最新音乐

发请求、渲染

```vue
<template>
  <div class="songs-container">
    <!-- 分类 -->
    <div class="tab-bar">
      <span
        v-for="item in typies"
        :key="item.type"
        :class="['item', item.type === type ? 'active' : '']"
        @click="type = item.type"
        >{{ item.name }}</span
      >
    </div>
    <!-- 底下内容区域 -->
    <table class="el-table playlit-table">
      <thead>
        <tr>
          <th></th>
          <th></th>
          <th>音乐标题</th>
          <th>歌手</th>
          <th>专辑</th>
          <th>时长</th>
        </tr>
      </thead>
      <tbody>
        <tr
          class="el-table__row"
          v-for="(item, index) in songList"
          :key="item.id"
        >
          <td>{{ index + 1 }}</td>
          <td>
            <div class="img-wrap">
              <img :src="item.album.picUrl" alt="" /><span
                class="iconfont icon-play"
              ></span>
            </div>
          </td>
          <td>
            <div class="song-wrap">
              <div class="name-wrap">
                <span>{{ item.name }}</span
                ><span v-if="item.mvid" class="iconfont icon-mv"></span>
              </div>
              <span></span>
            </div>
          </td>
          <td>{{ item.artists[0].name }}</td>
          <td>{{ item.album.name }}</td>
          <td>{{ item.duration | formatSecond }}</td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
export default {
  name: 'Songs',
  data () {
    return {
      type: 0, // 0：全部   7：华语  96：欧美  8：日本  16：韩语
      typies: [
        { type: 0, name: '全部' },
        { type: 7, name: '华语' },
        { type: 96, name: '欧美' },
        { type: 8, name: '日本' },
        { type: 16, name: '韩语' }
      ],
      songList: [] // 最新音乐列表
    }
  },
  mounted () {
    this.getSongListData()
  },
  watch: {
    type () {
      this.getSongListData()
    }
  },
  methods: {
    async getSongListData () {
      const res = await this.$http.get('top/song', {
        params: {
          type: this.type
        }
      })

      this.songList = res.data
    }
  }
}
</script>

<style lang="less">
.songs-container {
  .tab-bar {
    display: flex;
    justify-content: flex-end;
    .item {
      font-size: 15px;
      color: gray;
      margin-right: 20px;
      cursor: pointer;
      &.active {
        color: #dd6d60;
      }
    }
  }
  .song-table {
    .song-wrap {
      > span {
        margin-top: 20px;
        display: inline-block;
        color: #bebebe;
      }
      .icon-mv {
        padding-left: 5px;
        color: #dd6d60;
      }
    }
    .img-wrap {
      position: relative;
      width: 70px;
      height: 70px;
      img {
        width: 70px;
        height: 70px;
        border-radius: 5px;
      }
      .icon-play {
        position: absolute;
        top: 50%;
        left: 50%;
        transform: translate(-50%, -50%);
        width: 25px;
        height: 25px;
        color: #dd6d60;
        font-size: 12px;
        border-radius: 50%;
        display: flex;
        align-items: center;
        justify-content: center;
        background: rgba(255, 255, 255, 0.8);
        &::before {
          transform: translateX(1px);
        }
      }
    }
  }
}
</style>
```

## 最新MV

发请求，获取数据，然后渲染，注意：**有3个地方，需要watch**

```vue
<template>
  <div class="mvs-container">
    <!-- 筛选条件 -->
    <div class="filter-wrap">
      <div class="seciton-wrap">
        <span class="section-type">地区:</span>
        <ul class="tabs-wrap">
          <li
            class="tab"
            v-for="item in areas"
            :key="item"
            @click="area = item"
          >
            <span :class="['title', item === area ? 'active' : '']">{{
              item
            }}</span>
          </li>
        </ul>
      </div>
      <div class="type-wrap">
        <span class="type-type">类型:</span>
        <ul class="tabs-wrap">
          <li
            class="tab"
            v-for="item in types"
            :key="item"
            @click="type = item"
          >
            <span :class="['title', item === type ? 'active' : '']">{{
              item
            }}</span>
          </li>
        </ul>
      </div>
      <div class="order-wrap">
        <span class="order-type">排序:</span>
        <ul class="tabs-wrap">
          <li
            class="tab"
            v-for="item in orders"
            :key="item"
            @click="order = item"
          >
            <span :class="['title', item === order ? 'active' : '']">{{
              item
            }}</span>
          </li>
        </ul>
      </div>
    </div>
    <!-- 内容 -->
    <div class="mvs">
      <div class="items">
        <div class="item" v-for="item in mvList" :key="item.id">
          <a href="#/mv/10756067" class=""
            ><div class="img-wrap">
              <img :src="item.cover" alt="" />
              <div class="num-wrap">
                <div class="iconfont icon-play"></div>
                <div class="num">{{ item.playCount | formatCount }}</div>
              </div>
            </div>
            <div class="info-wrap">
              <div class="name">{{ item.name }}</div>
            </div></a
          >
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data () {
    return {
      area: '全部',
      areas: ['全部', '内地', '港台', '欧美', '日本', '韩国'],
      type: '全部',
      types: ['全部', '官方版', '原声', '现场版', '网易出品'],
      order: '上升最快',
      orders: ['上升最快', '最热', '最新'],
      mvList: []
    }
  },
  mounted () {
    this.getMvsListData()
  },
  watch: {
    area () {
      this.getMvsListData()
    },
    type () {
      this.getMvsListData()
    },
    order () {
      this.getMvsListData()
    }
  },
  methods: {
    async getMvsListData () {
      const res = await this.$http.get('mv/all', {
        params: {
          offset: 0,
          limit: 12,
          area: this.area,
          type: this.type,
          order: this.order
        }
      })

      this.mvList = res.data
    }
  }
}
</script>

<style lang="less">
.mvs-container {
  padding-top: 20px;
  .filter-wrap {
    > div {
      margin-bottom: 30px;
      vertical-align: baseline;
      display: flex;
      align-items: center;
      span {
        font-size: 14px;
        height: 100%;
        &.title {
          margin: 20px;
          color: gray;
          cursor: pointer;
          padding: 5px 20px;
          &.active {
            color: #dd6d60;
            background-color: #fcf6f5;
            border-radius: 20px;
          }
          &::before {
            content: '', pos;
          }
        }
      }
      ul {
        display: flex;
        li:not(:last-child) {
          border-right: 1px solid #f2f2f1;
        }
      }
    }
  }
  .mvs {
    .items {
      display: flex;
      justify-content: flex-start;
      flex-wrap: wrap;
      .item {
        width: 250px;
        cursor: pointer;
        margin-right: 25px;
        margin-bottom: 30px;
        .img-wrap {
          width: 100%;
          position: relative;
          img {
            width: 100%;
            border-radius: 5px;
          }
          .num-wrap {
            position: absolute;
            color: white;
            top: 0;
            right: 0;
            display: flex;
            align-content: center;
            font-size: 15px;
            padding-right: 5px;
            padding-top: 2px;
            .icon-play {
              font-size: 12px;
              display: flex;
              align-items: center;
              margin-right: 5px;
            }
          }
        }
        .info-wrap {
          .name {
            font-size: 15px;
          }
          .singer {
            font-size: 14px;
            color: #c5c5c5;
          }
        }
      }
    }
  }
}
</style>
```

## MV详情

步骤：

1. 在MV列表中，通过声明式导航跳转到MV详情页面

2. 创建`Mv.vue`页面，并且在`src/router/index.js`中配置好路由规则

   ```js
   // params 方式传参
   { path: '/mv/:id', component: Mv }
   ```

3. 发送请求，发送四个

   ```vue
   <script>
   export default {
     name: 'Mv',
     data () {
       return {
         // mv地址
         mvUrl: '',
         // mv的名字
         mvName: '',
         // 播放次数
         playCount: '',
         // 发布时间
         publishTime: '',
         // 描述
         desc: '',
         // 歌手名
         artistName: '',
         // 封面
         artistCover: '',
         // 相关推荐MV
         simiMVList: []
       }
     },
     mounted () {
       // 获取播放地址
       this.getMvUrlData()
       // 获取MV详情
       this.getMvDetailData()
       // 获取相关mv列表
       this.getSimiMvListData()
     },
     methods: {
       async getMvUrlData () {
         const res = await this.$http.get(`mv/url?id=${this.$route.params.id}`)
   
         this.mvUrl = res.data.url
       },
       async getMvDetailData () {
         const res = await this.$http.get(
           `mv/detail?mvid=${this.$route.params.id}`
         )
   
         this.mvName = res.data.name
         this.playCount = res.data.playCount
         this.publishTime = res.data.publishTime
         this.desc = res.data.desc
   
         // 获取艺术家信息
         const res2 = await this.$http.get(`artists?id=${res.data.artistId}`)
         this.artistName = res2.artist.name
         this.artistCover = res2.artist.picUrl
       },
       async getSimiMvListData () {
         const res = await this.$http.get(`simi/mv?mvid=${this.$route.params.id}`)
   
         this.simiMVList = res.mvs
       }
     }
   }
   </script>
   ```

4. 渲染

   ```vue
   <template>
     <div class="mv-container">
       <div class="mv-wrap">
         <h3 class="title">mv详情</h3>
         <div class="video-wrap">
           <video controls="controls" :src="mvUrl" autoplay></video>
         </div>
         <div class="info-wrap">
           <div class="singer-info">
             <div class="avatar-wrap">
               <img :src="artistCover" alt="" />
             </div>
             <span class="name">
               {{ artistName }}
             </span>
           </div>
           <div class="mv-info">
             <h2 class="title">{{ mvName }}</h2>
             <span class="date">发布：{{ publishTime }}</span
             ><span class="number">播放：{{ playCount | formatCount }}次</span>
             <p class="desc">{{ desc }}</p>
           </div>
         </div>
       </div>
       <div class="mv-recommend">
         <h3 class="title">相关推荐</h3>
         <div class="mvs">
           <div class="items" v-for="item in simiMVList" :key="item.id">
             <div class="item">
               <div class="img-wrap">
                 <img :src="item.cover" alt="" /><span
                   class="iconfont icon-play"
                 ></span>
                 <div class="num-wrap">
                   <div class="iconfont icon-play"></div>
                   <div class="num">{{ item.playCount | formatCount }}</div>
                 </div>
                 <span class="time">{{ item.duration | formatSecond }}</span>
               </div>
               <div class="info-wrap">
                 <div class="name">{{ item.name }}</div>
                 <div class="singer">{{ item.artistName }}</div>
               </div>
             </div>
           </div>
         </div>
       </div>
     </div>
   </template>
   ```

点击右侧，切换内容显示：

1. 给右边添加点击事件

2. 我们通过编程式导航，跳转到mv页面

3. 使用watch监听`$route`、`$route.params`、`$route.params.id`都可以监听路径的改变

   ```vue
   <script>
   	export default {
           watch: {
           // $route () {
           //   // 获取播放地址
           //   this.getMvUrlData()
           //   // 获取MV详情
           //   this.getMvDetailData()
           //   // 获取相关mv列表
           //   this.getSimiMvListData()
           // }
           // '$route.params' () {
           //   // 获取播放地址
           //   this.getMvUrlData()
           //   // 获取MV详情
           //   this.getMvDetailData()
           //   // 获取相关mv列表
           //   this.getSimiMvListData()
           // }
           '$route.params.id' () {
             // 获取播放地址
             this.getMvUrlData()
             // 获取MV详情
             this.getMvDetailData()
             // 获取相关mv列表
             this.getSimiMvListData()
           }
         }
       }
   </script>
   ```

## 音频播放

当点击`发现音乐`、`歌单详情`、`最新音乐`中的播放按钮的时候，实现音乐播放

1. 创建一个公共bus

   ![image-20201020152951982](assets/image-20201020152951982.png)

2. 在传值方，使用公共bus触发自定义的播放事件，并且传值

   ![image-20201020153012370](assets/image-20201020153012370.png)

3. 在接收方，使用公共bus监听播放事件，并且发请求，获取音频的url

   ![image-20201020153032305](assets/image-20201020153032305.png)

个除了正常播放音频之外，我们来到MV页面的时候，需要暂停音频播放

1. 来到MV页面的时候，我在mounted钩子中，就直接调用bus，触发一个audiopause事件

   ![image-20201020153117649](assets/image-20201020153117649.png)

2. 在MyAudio组件中，监听播放暂停的方法，实现音乐暂停

   ![image-20201020153146323](assets/image-20201020153146323.png)
   
   
   --------------------------vue总结-----------------
   


## Vue相关
### 什么是数据响应式？怎么实现的？明显缺点？
```
vue会对这个数据监听处理，它会通过这个Object.defineProperty()来对数据进行处理，使它变成setter，getter形式，这样就可以监听变化，同时可以在这个数据变化的时候在ui上展示更新变化之后的数据。
缺点：不能监听数组，做有效处理。vue3可以。还有就是一开始没有后面新加的。但是可以用set方法来添加监听。
```

### Vue 的双向数据绑定原理是什么？
>```shell
>采用数据劫持结合发布者-订阅者模式的方式，通过Object.defineProperty()来劫持各个属性的setter，getter，在数据变动时发布消息给订阅者，触发相应的监听回调。
>```

### Vue的优点？
>```
>1、轻量级框架，大小只有几十kb、简单易学
>2、保留了angular的特点，双向数据绑定数据操作方便
>3、组件化开发实现了html的封装和重用，在构建单页面有独特优势
>4、虚拟DOM操作非常消耗性能，不使用原生DOM操作，换了种方式，就操作DOM而言相比react性能有更到优势
>```

### Vue实现组件通信方式？
```
1.props  // 一般是实现父传子，隔代麻烦
2.vue自定义事件  // 只适合子传父
3.vuex  // 对组件关系没有限制，方便
具体实现：vuex 是一个状态管理工具，主要解决大中型复杂项目的数据共享问题，主要包括state,actions,mutations,getters 和 modules 5 个要素，主要流程：组件通过 dispatch 到 actions，actions 是异步操作，再 actions中通过 commit 到 mutations，mutations 再通过逻辑操作改变 state，从而同步到组件，更新其数据状态。
4.slot  // 专门用于实现父传子传递带数据的标签
5.消息订阅与发布  // 可以实现任意关系组件间通信
```

### Vue父组件向子组件传递数据？
```
通过props
```

### Vue兄弟之间传递数据？
```
1、通过事件中心：可以用过一个vue实例Bus作为媒介，要相互通信的兄弟组件之中，都引入Bus，之后通过分别调用Bus事件触发$emit和监听$on来实现组件之间的通信和参数传递
Bus.$emit('name', 'hello');
Bus..$on('name',(value)=>{
  this.value=value;
});
2、子传父，父传子
```
### 子组件向父组件传递数据？
```
$emit()方法
```
### v-show和v-if的区别？
```
v-show是通过css中的display设置为none，控制隐藏只会编译一次
v-if是动态的向DOM树内添加或删除DOM元素，v-if不停的销毁和创建比较消耗性能
如果要频繁切换某个节点用v-show（切换开销小，初始开销大），如果不需要用v-if（初始渲染开销小，切换开销大）
```
### 如何让css只在当前组件中起作用？
```
在组件的style中添加scoped
```
### keep-alive的作用是什么?
```
keep-alive是vue内置的一个 组件，可以使被包含的组件保留状态，避免重新渲染
```
### Vue中如何获取DOM
```
ref="domName", 使用this.$refs.domName
```
### 说出几种vue当中的指令和它的法？
```
v-model双向数据绑定
v-for循环
v-if v-show显示与隐藏
v-on绑定事件、v-once:只绑定一次
```
### vue-loader是什么？用途？
```
vue-loader是vue文件的一个加载器，将template/js/style/转换成js模块。
用途：js可以写ES6、style样式可以写scss、less、template可以价jade等
```
### 为什么使用key?
```
需要使用key来给每个节点做一个唯一表示，vue自带的Diff算法就可以正确的识别此节点。
作用主要为了高效的更新虚拟DOM
```
### v-model的使用？
```
用于表单数据的双向绑定，这个背后有两个操作：
1、v-bind绑定value属性
2、v-on指令给当前元素绑定input事件
```
### vue-cli创建的项目中src目录每个文件夹和文件的用法？
```
assets存放静态资源
components是存放组件
router定义路由相关的配置
app.vue是一个应用主组件
main.js入口文件
```
### 分别阐述computed和watch的使用场景
```
computed：
   当一个属性受多个属性影响的时候就需要用到computed
   典型例子：购物车结算的时候
watch：
   当一条数据影响多条数据的时候就需要用到watch,监听事件变化
   典型例子：搜索数据
```
### v-on可以监听多个方法？
```
v-on="{ input: onInput, focus: onFocus, blur: onBlur }"
```
### $nextTick的使用？

```
当你修改data的值然后马上获取这个dom元素的值，是不能获取到更新后的值。
可以用$nextTick这个回调，让修改后的data值渲染更新到dom之后在获取，才能成功。
在created()钩子函数执行的时候DOM 其实并未进行任何渲染，而此时进行DOM操作并无作用，而在created()里使用this.$nextTick()可以等待dom生成以后再来获取dom对象
```
### 为什么组件中data必须是一个函数？
```
数据以函数的的形式定义，这样在组件复用的时候，就都会返回一份新的data,每个组件实例都有自己独立的数据空间，不会造成数据混乱。而对象定义的形式，就是所有组件共用一个data,这样改一个全都改了。
```
### 如何理解渐进式框架?
```
根据需要来选择层级
```
### Vue中双向数据绑定是如何实现的？
```
vue双向绑定时通过数据劫持，结合 发布订阅模式的方式来实现的，也就是说数据和视图同步，数据变化，视图也跟着变化，数据也随之发送变化
核心：原理是观察者observer通过Object.defineProperty()来劫持到各个属性的getter setter，在数据变动的时候，会被observer观察到，会通过Dep通知数据的订阅者watcher，之后进行相应的视图上面的变化。
```
### 单页面应用和多页面应用区别及优缺点？
```
单页面缺点：不利于seo，导航不可用（不能使用浏览器的前进后退功能），如果一定要实现前进后退，需要自己建立堆栈管理。初次加载耗时多，
```
### v-if和v-for的优先级？
```
当v-if一起使用时，v-for优先级高，要一起使用v-if应该放到外面
永远不要把 v-if 和 v-for 同时用在同一个元素上。
将 users替换为一个计算属性 (比如 activeUsers)，让其返回过滤后的列表
将：
<ul>
  <li
    v-for="user in users"
    v-if="user.isActive"
    :key="user.id"
  >
    {{ user.name }}
  </li>
</ul>


换成：

<ul>
  <li
    v-for="user in activeUsers"
    :key="user.id"
  >
    {{ user.name }}
  </li>
</ul>
computed: {
  activeUsers: function () {
    return this.users.filter(function (user) {
      return user.isActive
    })
  }
}

```
### Vue常用的修饰符?
```
.stop防止事件冒泡，等同于event.stopPropagation()
.prevent阻止默认行为，等同于event.preventDefault()
.self只触发自己范围内的事件，不包含子元素
.once只触发一次
```
### Vue的两个核心点？
```
数据驱动、组件系统

数据驱动：ViewModel，保证数据和视图一致
组件系统：应用类UI可以看作全部是由组件树构成的
```
### Vue和Jq的区别？
```
jq是使用选择器 $() 选取DOM对象，对其进行赋值、取值、事件绑定等操作，和原生的html的区别在于可以方便选取操作DOM对象，而数据和界面是在一起的。
vue是通过对象将数据和View分离，进行数据操作不再需要应用相对应的DOM对象，他们通过Vue对象这个vm实现相互的绑定，这就是MVVM。
```
### Vue里面router-link在电脑上有用，在安卓上没反应怎么解决？
```
有babel问题，安装babel polypill插件解决
```
### Vue中注册在router-link上事件无效解决方法
```
使用@click.native  因为router-link会阻止click事件，native指直接监听一个原生事件。
```
### Vue闪动问题？
```
[v-cloak] { display: none; }
```
### Vue更新数组时触发视图更新的方法？
```
push()、pop()、shift()、unshift()、splice()、sort()、reverse()
```
### 什么是生命周期，作用？
```
每个Vue实例在被创建时都要经过一系列的初始化过程，例如需要设置数据监听、编译模板、将实例挂载到DOM并在数据变化时更新DOM。这一过程会运行一些生命周期钩子函数，这给用户在不同阶段添加自己的代码机会。例如：如果要通过某些插件操作DOM节点，如果想在页面渲染完后弹出广告，那我们最早可在mounted中进行。
```

### 第一次页面加载会触发哪几个钩子函数？
```
beforeCreate、created、beforeMount、mounted
```
### 钩子函数具体适合那些场景？
>```shell
>beforeCreate：在new一个vue实例后，只有一些默认的生命周期钩子和默认事件，其他的东西都还没有创建。在beforeCreate生命周期执行的时候，data和methods中的数据都还没有初始化。不能在这个阶段使用data中的数据和methods中的方法。
>create：data和methods都已经被初始化好了，如果要调用methods中的方法，或者操作data中的数据，最早可以在这个阶段中操作。
>beforeMount：执行到这个钩子函数的时候，内存中已经编译好了模板，但是还没有挂载到页面中，此时，页面还是旧的
>mounted：执行到这个钩子函数的时候，就表示Vue实例已经初始化完成了。此时组件脱离了创建阶段，进入到运行阶段。如果想要通过插件操作页面上的DOM节点，最早可以在这个阶段中进行。
>beforeUpdate：执行到这个钩子函数时，页面中的显示数据还是旧的，data中的数据时更新后的，页面还没有和最新的数据保持同步。
>updated：页面显示的数据和data中的数据已经保持同步了，都是最新的
>beforeDestory：Vue实例从运行阶段进入到销毁阶段，这个时候上所有的data和methods，指令，过滤器...都处于可用状态。还没有真正被销毁。
>destoryed：这个时候上所有的data和methods，指令，过滤器...都处于不可用状态。组件已经被销毁。
>```

### created和mounted的区别？
```
created：在模板渲染成html前调用，即通常初始化某些属性值，然后再渲染成视图。
mounted：在模板渲染成html后调用，通常是初始化页面完成后，在对html和dom节点进行操作。
```

### watch、computed和methods的区别
```
computed 计算属性 计算结果会缓存,只有当依赖值改变才会重新计算

watch 监听属性 一个值的改变  需要另一个值的改变而改变,结果不会缓存

methods 事件方法 调用一次，执行一次,结果不会缓存
```

### vue获取数据在哪个周期函数？
```
在created/beforeMount/mounted皆可
但如果你要操作dom，那么肯定mounted时候才能操作
```
### 请详细说下你对vue生命周期的理解？
```
总共分8个阶段：创建前/后  载入前/后  更新前/后  销毁前/后

创建前/后：在beforeCreated阶段，vue实例的挂载元素$el（节点）和数据对象data都为undefined，还未初始化。在created阶段，vue实例的数据对象data有了，$el还没有。

载入前/后：在berforeMount阶段，vue实例的$el和data都初始化了，但还是挂载之前虚拟的DOM节点，data.message还未替换。
在mounted阶段，vue实例挂载完成，data.message成功渲染。

更新前/后：在data变化时，会触发beforeUpdate和updata方法。

销毁前/后：在执行destroy方法后，对data的改变不会触发周期函数，说明此时vue实例已经解除了事件监听以及dom的绑定，但dom结构依然存在。
```

### MVVM框架是什么？
vue是实现了双向数据绑定的mvvm框架，当视图改变更新模型层，当模型层该变更新视图层。在vue中，使用了双向绑定技术，就是view的变化能实时让Model发送变化，而Model的变化也能实时更新到view。

#### vue弹窗后如何禁止滚动条滚动？
```

```
### vue-cli中自定义指令的使用？
```
bind：只调用一次，指令第一次绑定到元素时调用。在这里可以进行一次性的初始化设置。
inserted：被绑定元素插入父节点时调用 (仅保证父节点存在，但不一定已被插入文档中)。
update：所在组件的 VNode 更新时调用，但是可能发生在其子 VNode 更新之前。指令的值可能发生了改变，也可能没有。但是你可以通过比较更新前后的值来忽略不必要的模板更新 。
componentUpdated：指令所在组件的 VNode 及其子 VNode 全部更新后调用。
unbind：只调用一次，指令与元素解绑时调用。
```

### vue中对对象更改检测的注意事项？
```js
Vue不能检测对象属性的添加或删除：
var vm = new Vue({
  data: {
    a: 1
  }
})
// vm.a 现在是响应式的
vm.b = 2 // vm.b 不是响应式的

对于已经创建的实例，vue不能动态添加根级别的响应式属性。但是，可以使用 Vue.set(object, key, value)  方法向嵌套对象添加响应式属性。例如
var vm = new Vue({
  data: {
    userProfile: {
      name: 'Anika'
    }
  }
})
可以使用set来添加一个新的age属性到嵌套到对象中
Vue.set(vm.userProfile, 'age', 21)
或者 vm.$set(vm.userProfile, 'age', 27)  // vm.$set只是全局Vue.set的别名 
```

### vue子组件如何调用父组件的方法？
```js
1、直接在子组件中通过  this.$parent.事件名  来调用父组件的方法
2、在子组件里用$emit向父组件触发一个事件，父组件监听这个事件就行了
3、父组件把方法传入子组件中，在子组件里直接调用这个方法。
props: {
  fatherMethod: {
    type: Function,
    default: null
  }
},
methods: {
  childMethod() {
    if (this.fatherMethod) {
        this.fatherMethod();
      }
    }
  }
```

## vue-router
### vue-router是什么？它有那些组件？
```
vue用来写路由的一个插件。router-link、router-view
```

### vue-router有哪几种导航钩子?
```js
① 全局导航钩子：一般用来判断权限，以及页面丢失时需要执行的操作；
const router = new VueRouter({ ... });
router.beforeEach((to, from, next) => {
    // do someting
});
1.to: Route，代表要进入的目标，它是一个路由对象

2.from: Route，代表当前正要离开的路由，同样也是一个路由对象

3.next: Function，这是一个必须需要调用的方法，而具体的执行效果则依赖 next 方法调用的参数

     beforeEach（）每次路由进入之前执行的函数。
     afterEach（）每次路由进入之后执行的函数。
     beforeResolve（）2.5新增
② 单个路由（实例钩子）即单个路由独享的导航钩子，它是在路由配置上直接进行定义的：
cont router = new VueRouter({
    routes: [
        {
            path: '/file',
            component: File,
            beforeEnter: (to, from ,next) => {
                // do someting
            }
        }
    ]
});
     beforeEnter（）
     beforeLeave（）
③ 组件路由钩子：
    beforeRouteEnter（）
    beforeRouteLeave（）
    beforeRouteUpdate（）
    他们是直接在路由组件内部直接进行定义的
    const File = {
    template: `<div>This is file</div>`,
    beforeRouteEnter(to, from, next) {
        // do someting
        // 在渲染该组件的对应路由被 confirm 前调用
    },
    beforeRouteUpdate(to, from, next) {
        // do someting
        // 在当前路由改变，但是依然渲染该组件是调用
    },
    beforeRouteLeave(to, from ,next) {
        // do someting
        // 导航离开该组件的对应路由时被调用
    }
}
```

### active-class是哪个组件的属性？
```
vue-router模块的router-link组件中的属性，用来设置链接激活时使用的类名。
```

### vue-router实例方法？ vue-router的动态路由传参？怎么传递参数与获取传过来的值？
```js
实例方法：push、replace、go
replace和push都能实现跳转的效果，但是区别在于：唯一的不同就是，它不会向 history 添加新记录，而是跟它的方法名一样 —— 替换掉当前的 history 记录。

定义：在router目录下的index.js文件中，对path属性加上 /:id。

1、params 方式   // 相当于ajax的post请求
配置
{
  path:"/detail",
  name:"detail",
  component:home
}
传递
this.$router.push({
   name:"/detail",  // 一定要使用name匹配
   params:{
   name:'nameValue',
   code:10011
  }
});
接受
this.$route.params.code    
params类似于post===>浏览器地址栏中不显示参数


2、query 方式
配置
{
  path:"/detail",
  name:"detail",   // 可以不需要
  component:home
}
传递
this.$router.push({
   path:"/detail",  // 用path匹配，也可以用name
   query:{
   name:'nameValue',
   code:10011
  }
});
接受
this.$route.query.code    
query类似于ajax的get传参==>浏览器地址栏中显示参数
3、
<router-link :to="{ A: 'xxx', query: { xx:'xxx' }}" />
<router-link :to="{ A: 'xxx', parmas: { xx:'xxx' }}" />

4、props的值还可以为对象类型props: { user:''}
配置中
{
  path: '/article/:articleId',
  name: 'article',
  component: () => import('../views/article/'),
  props: true
},
组件中
props: {
  articleId: {
    type: [Object, String, Number],
    required: true
  }
},
```

### params、query两者区别？
```js
this.$router.push() 方法中，params传值必须有name属性，也可以有path属性，不然取不到值。
this.$router.push() 方法中，query传值与name和path属性无必然联系，和其中哪个属性搭配都可以传值。
params传值在url上是看不到传递的参数的。
通过query传值，跳到另一个页面的时候，刷新还是可以取到传递过来的值，而params就会重置，取不到值。

最近有一个需求，比如详情页，要求按F5刷新完后数据还是能正常展示，详情页是在created后用ID请求。
如果是用query 传递过来的id，在this.$route，上会一直存在。
但是如果用params的时候，如果不做别的配置，直接在路由跳转的时间加params，F5刷新数据可能就不存在了。
如果一定要用params也可以，在router文件的 path 后面 + “/:id”，这样页面F5刷新后ID还是在router中。
如果是单独的详情页这样也是可以的，但是如果新增和编辑都是跳转同一个路由呢，这样就会报错了，因为编辑要请求详情，就需要ID，但是新增的时候是没有ID的
这时候就需要   path 后面 + “/:id?”，也就是id后面加一个“？”，和正则的意思一样，可有可无。这样就不会报错了。
个人还是建议用 query ，因为它不需要改变 path规则。
```

### $route和$router的区别？
```
$route为当前router信息对象。里面可以获取到当前路由的name,path,query,params等
$router是VueRouter的实例，使用$router.push方法可以导航到不同的url。返回上一个历史history用$router.go(-1)
```

### vue-router如何响应路由参数的变化？
```js
1、监听器
watch: { // watch的第一种写法
    $route (to, from) {
        console.log(to)
        console.log(from)
    }
},
watch: { // watch的第二种写法
    $route: {
        handler (to, from){
            console.log(to)
            console.log(from)
        },
        // 深度观察监听
        deep: true
    }
},

watch: { // watch的第三种写法
    '$route':'getPath'
},
methods: {
    getPath(to, from){
        console.log(this.$route.path);
    }
},


2、导航守卫
```
### 怎么复用一个组件？
```
```
### 路由懒加载？
```
```

## vuex
### vuex是什么？怎么使用？功能场景？
```
定义：Vuex是一个专为Vue.js应用程序开发的状态管理模式。它采用集中式储存管理应用的所有组件的状态，并以相应的规则保证状态以一种可预测的方式发生变化。

使用场景：需要构建一个中大型单页应用，您很可能会考虑如何更好的在组件外部管理状态，Vuex将会成为自然而然的选择。单页面应用中，组件之间的状态，音乐播放，登陆状态，加入购物车。

优点：当你在state中定义了一个数据之后，可以在所在项目中的任何一个组件里进行获取、进行修改、并且你的修改可以得到全局的响应变更。

 Vuex的运行机制：在组件中通过this.$store.dispatch来调用actions中的方法，在action中通过commit来调用mutations中的方法，在mutations的方法中操作state中的数据，数据只要更新就会立即响应到组件上

 核心：
      ①state：定义初始数据。
      ②mutations：更改Vuex的store中的状态的唯一方法是提交mutation
      ③getters：可以对state 进行计算操作，它就是 store 的计算属性虽然在组件内也可以做计算属性，但是 getters 可以在多给件之间复用如果一个状态只在一个组件内使用，是可以不用 getters。
      ④actions：异步操作初始数据，其实就是调用mutations里面的方法。
      ⑤module：面对复杂的应用程序，当管理的状态比较多时；我们需要将vuex的store对象分割成模块(modules)。
```

### vuex有哪几种属性？
```
State、Getter、Mutation、Action、Module
state => 基本数据
getter => 从基本数据派生出来的数据
mutations => 提交更新数据的方法，同步
actions => 包裹mutation，使之可以异步
module => 模块化vuex
```

### vue.js中ajax请求代码应该写在组件的methods中还是vuex中的actions中？
```
如果请求未来的数据是不是要被其他组件公用，仅仅在请求的组件内使用，就不要存放在vuex的state里。
```



