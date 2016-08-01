   function showLogin() {
            var loginDiv = document.getElementById("loginDiv");
            var clientx = document.documentElement.clientWidth;
            var clienty = document.documentElement.clientHeight;
            var l_margin = (clientx - parseInt(loginDiv.style.width)) / 2;
            var t_margin = (clienty - parseInt(loginDiv.style.height)-200) / 2
            loginDiv.style.left = l_margin + "px";
            loginDiv.style.top = t_margin +"px";
            loginDiv.style.display = "block";
            scroll(0,0);
        }
        function hidLogin() {
            var loginDiv = document.getElementById("loginDiv");
            loginDiv.style.display = "none";
        }
        function titleMove() {
            var moveable = true;
            var loginDiv = document.getElementById("loginDiv");
            var clientX = window.event.clientX;
            var clientY = window.event.clientY;
            var moveTop = parseInt(loginDiv.style.top);
            var moveLeft = parseInt(loginDiv.style.left);
            document.onmousemove = function MouseMove() {
                if (moveable) {
                    var y = moveTop + window.event.clientY - clientY;
                    var x = moveLeft + window.event.clientX - clientX;
                    if (x > 0 && y > 0) {
                        loginDiv.style.top = y + "px";
                        loginDiv.style.left = x + "px";
                    }
                }
            }
            document.onmouseup = function Mouseup() {
                moveable = false;
            }
        }