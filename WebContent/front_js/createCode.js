//设置一个全局的变量，便于保存验证码
    var code;
    function createCode(){
        //首先默认code为空字符串
        code = '';
        //设置长度，这里看需求，我这里设置了4
        var codeLength = 4;
        var codeV = document.getElementById('codeone');
        //设置随机字符
        var random = new Array(0,1,2,3,4,5,6,7,8,9,'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R', 'S','T','U','V','W','X','Y','Z');
        //循环codeLength 我设置的4就是循环4次
        for(var i = 0; i < codeLength; i++){
            //设置随机数范围,这设置为0 ~ 36
             var index = Math.floor(Math.random()*36);
             //字符串拼接 将每次随机的字符 进行拼接
             code += random[index]; 
        }
        //将拼接好的字符串赋值给展示的Value
        codeV.innerHTML = code;
    }
    //设置此处的原因是每次进入界面展示一个随机的验证码，不设置则为空
    window.onload = function (){
        createCode();
    }