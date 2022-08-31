<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="shortcut icon" type="image/x-icon" href="backend/img/smalllogo.png" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"><!-- Vendor styles -->
    <link rel="stylesheet" href="backend/vendors/material-design-iconic-font/css/material-design-iconic-font.min.css">
    <link rel="stylesheet" href="backend/vendors/animate.css/animate.min.css">
    <link rel="stylesheet" href="backend/vendors/jquery-scrollbar/jquery.scrollbar.css">
    <link rel="stylesheet" href="backend/vendors/fullcalendar/fullcalendar.min.css"><!-- App styles -->
    <link rel="stylesheet" href="backend/css/app.min.css">
<!--     原 -->
<!--     <link href="https://fonts.googleapis.com/css?family=Montserrat:300,300i,400,400i,500,500i,600,600i,700,700i&display=swap" rel="stylesheet"> -->
<!--     <link href="https://fonts.googleapis.com/css?family=Lato:300,300i,400,400i,700,700i&display=swap" rel="stylesheet"> -->
<!--     <link rel="stylesheet" href="./css/bootstrap.min.css"> -->
<!--     <link rel="stylesheet" href="./css/font-awesome.min.css"> -->
<!--     <link rel="stylesheet" href="./css/owl.carousel.min.css"> -->
<!--     <link rel="stylesheet" href="./css/animate.min.css"> -->
<!--     <link rel="stylesheet" href="./css/jquery-ui.css"> -->
<!--     <link rel="stylesheet" href="./css/slick.css"> -->
<!--     <link rel="stylesheet" href="./css/chosen.min.css"> -->
<!--     <link rel="stylesheet" href="./css/pe-icon-7-stroke.css"> -->
<!--     <link rel="stylesheet" href="./css/magnific-popup.min.css"> -->
<!--     <link rel="stylesheet" href="./css/lightbox.min.css"> -->
<!--     <link rel="stylesheet" href="./js/fancybox/source/jquery.fancybox.css"> -->
<!--     <link rel="stylesheet" href="./css/jquery.scrollbar.min.css"> -->
<!--     <link rel="stylesheet" href="./css/mobile-menu.css"> -->
<!--     <link rel="stylesheet" href="./fonts/flaticon/flaticon.css"> -->
<!--     <link rel="stylesheet" href="./css/style.css"> -->
<title>好學生後台管理首頁</title>
</head>
<body data-ma-theme="green">
<main class="main">
        <div class="page-loader">
            <div class="page-loader__spinner"><svg viewBox="25 25 50 50">
                    <circle cx="50" cy="50" r="20" fill="none" stroke-width="2" stroke-miterlimit="10" />
                </svg></div>
        </div>
        <header class="header">
            <div class="navigation-trigger hidden-xl-up" data-ma-action="aside-open" data-ma-target=".sidebar">
                <div class="navigation-trigger__inner"><i class="navigation-trigger__line"></i><i
                        class="navigation-trigger__line"></i><i class="navigation-trigger__line"></i></div>
            </div>
            <div class="header__logo hidden-sm-down">
                <h1><a href="BackendIndex.jsp"><img src="backend/img/logo.png" width="125px"></a></h1>
            </div>
<!--             <form class="search"> -->
<!--                 <div class="search__inner"><input type="text" class="search__text" placeholder="搜尋..." size="30px"><i -->
<!--                         class="zmdi zmdi-search search__helper" data-ma-action="search-close"></i></div> -->
<!--             </form> -->
            <ul class="top-nav">
                <li class="hidden-xl-up"><a href="" data-ma-action="search-open"><i class="zmdi zmdi-search"></i></a>
                </li>

                <li class="dropdown top-nav__notifications"><a href="" data-toggle="dropdown" class="top-nav__notify"><i
                            class="zmdi zmdi-notifications"></i></a>
                    <div class="dropdown-menu dropdown-menu-right dropdown-menu--block">
                        <div class="listview listview--hover">
                            <div class="listview__header">通知<div class="actions"><a href=""
                                        class="actions__item zmdi zmdi-check-all"
                                        data-ma-action="notifications-clear"></a></div>
                            </div>
                            <div class="listview__scroll scrollbar-inner"><a href="" class="listview__item"><img
                                        src="demo/img/profile-pics/1.jpg" class="listview__img" alt="">
                                    <div class="listview__content">
                                        <div class="listview__heading">David Belle</div>
                                        <p>Cum sociis natoque penatibus et magnis dis parturient montes</p>
                                    </div>
                                </a></div>
                            <div class="p-1"></div>
                        </div>
                    </div>
                </li>

<!--                 <li class="dropdown hidden-xs-down"><a href="" data-toggle="dropdown"><i -->
<!--                             class="zmdi zmdi-more-vert"></i></a> -->
<!--                     <div class="dropdown-menu dropdown-menu-right"> -->
<!--                         <div class="dropdown-item theme-switch"> 頁面色彩選擇<div -->
<!--                                 class="btn-group btn-group-toggle btn-group--colors" data-toggle="buttons"><label -->
<!--                                     class="btn bg-green active"><input type="radio" value="green" autocomplete="off" -->
<!--                                         checked></label><label class="btn bg-blue"><input type="radio" value="blue" -->
<!--                                         autocomplete="off"></label><label class="btn bg-red"><input type="radio" -->
<!--                                         value="red" autocomplete="off"></label><label class="btn bg-orange"><input -->
<!--                                         type="radio" value="orange" autocomplete="off"></label><label -->
<!--                                     class="btn bg-teal"><input type="radio" value="teal" autocomplete="off"></label> -->
<!--                                 <div class="clearfix mt-2"></div><label class="btn bg-cyan"><input type="radio" -->
<!--                                         value="cyan" autocomplete="off"></label><label class="btn bg-blue-grey"><input -->
<!--                                         type="radio" value="blue-grey" autocomplete="off"></label><label -->
<!--                                     class="btn bg-purple"><input type="radio" value="purple" -->
<!--                                         autocomplete="off"></label><label class="btn bg-indigo"><input type="radio" -->
<!--                                         value="indigo" autocomplete="off"></label><label class="btn bg-brown"><input -->
<!--                                         type="radio" value="brown" autocomplete="off"></label> -->
<!--                             </div> -->
<!--                         </div> -->
<!--                     </div> -->
<!--                 </li> -->
                <li>


                    <div class="user__info" data-toggle="dropdown"><img class="user__img"
                            src="backend/demo/img/profile-pics/8.jpg" alt="">

                    </div>
                    <div class="dropdown-menu"><a class="dropdown-item" href="">xxxxx</a><a class="dropdown-item"
                            href="">查看個人檔案</a><a class="dropdown-item" href="">登出</a></div>

                </li>
            </ul>
        </header>
        <aside class="sidebar">
            <div class="scrollbar-inner">

                <ul class="navigation">
                    <li class="navigation__active"><a href="Index.jsp"><i class="zmdi zmdi-home"></i>前台首頁</a></li>
                    <li><a href="typography.html"><i class="zmdi zmdi-format-underlined"></i>會員管理</a></li>
                    <li class="navigation__sub"><a href=""><i class="zmdi zmdi-view-week"></i>試卷管理</a>
                        <ul>
                            <li><a href="hidden-sidebar.html">全部試卷</a></li>
                            <li><a href="hidden-sidebar.html">國中</a></li>
                            <li><a href="boxed-layout.html">高中</a></li>
                            <li><a href="hidden-sidebar-boxed-layout.html">成人</a></li>
                        </ul>
                    </li>
                    <li class="navigation__sub"><a href=""><i class="zmdi zmdi-view-list"></i>課程管理</a>
                        <ul>
                            <li><a href="CourseServlet">全部課程</a></li>
                            <li><a href="hidden-sidebar.html">國中</a></li>
                            <li><a href="boxed-layout.html">高中</a></li>
                            <li><a href="hidden-sidebar-boxed-layout.html">成人</a></li>
                        </ul>
                    </li>

                    <li class="navigation__sub"><a href=""><i class="zmdi zmdi-collection-text"></i>專欄管理</a>
                    </li>
                    <li><a href="calendar.html"><i class="zmdi zmdi-calendar"></i>活動管理</a></li>

                    <li><a href="widgets.html"><i class="zmdi zmdi-widgets"></i>購物車管理</a></li>

                </ul>
            </div>
            
        </aside>

        
    </main><!-- Older IE warning message -->
    <!--[if IE]><div class="ie-warning"><h1>Warning!!</h1><p>You are using an outdated version of Internet Explorer, please upgrade to any of the following web browsers to access this website.</p><div class="ie-warning__downloads"><a href="http://www.google.com/chrome"><img src="img/browsers/chrome.png" alt=""></a><a href="https://www.mozilla.org/en-US/firefox/new"><img src="img/browsers/firefox.png" alt=""></a><a href="http://www.opera.com"><img src="img/browsers/opera.png" alt=""></a><a href="https://support.apple.com/downloads/safari"><img src="img/browsers/safari.png" alt=""></a><a href="https://www.microsoft.com/en-us/windows/microsoft-edge"><img src="img/browsers/edge.png" alt=""></a><a href="http://windows.microsoft.com/en-us/internet-explorer/download-ie"><img src="img/browsers/ie.png" alt=""></a></div><p>Sorry for the inconvenience!</p></div><![endif]-->
    <!-- Javascript -->
    <!-- Vendors -->
    <script src="backend/vendors/jquery/jquery.min.js"></script>
    <script src="backend/vendors/popper.js/popper.min.js"></script>
    <script src="backend/vendors/bootstrap/js/bootstrap.min.js"></script>
    <script src="backend/vendors/jquery-scrollbar/jquery.scrollbar.min.js"></script>
    <script src="backend/vendors/jquery-scrollLock/jquery-scrollLock.min.js"></script>
    <script src="backend/vendors/flot/jquery.flot.js"></script>
    <script src="backend/vendors/flot/jquery.flot.resize.js"></script>
    <script src="backend/vendors/flot.curvedlines/curvedLines.js"></script>
    <script src="backend/vendors/jqvmap/jquery.vmap.min.js"></script>
    <script src="backend/vendors/jqvmap/maps/jquery.vmap.world.js"></script>
    <script src="backend/vendors/easy-pie-chart/jquery.easypiechart.min.js"></script>
    <script src="backend/vendors/salvattore/salvattore.min.js"></script>
    <script src="backend/vendors/sparkline/jquery.sparkline.min.js"></script>
    <script src="backend/vendors/moment/moment.min.js"></script>
    <script src="backend/vendors/fullcalendar/fullcalendar.min.js"></script><!-- Charts and maps-->
    <script src="backend/demo/js/flot-charts/curved-line.js"></script>
    <script src="backend/demo/js/flot-charts/dynamic.js"></script>
    <script src="backend/demo/js/flot-charts/line.js"></script>
    <script src="backend/demo/js/flot-charts/chart-tooltips.js"></script>
    <script src="backend/demo/js/other-charts.js"></script>
    <script src="backend/demo/js/jqvmap.js"></script><!-- App functions and actions -->
    <script src="backend/js/app.min.js"></script>
    
<!--     原 -->
<!-- <script src="./js/jquery-1.12.4.min.js"></script> -->
<!--     <script src="./js/jquery.plugin-countdown.min.js"></script> -->
<!--     <script src="./js/jquery-countdown.min.js"></script> -->
<!--     <script src="./js/bootstrap.min.js"></script> -->
<!--     <script src="./js/owl.carousel.min.js"></script> -->
<!--     <script src="./js/magnific-popup.min.js"></script> -->
<!--     <script src="./js/isotope.min.js"></script> -->
<!--     <script src="./js/jquery.scrollbar.min.js"></script> -->
<!--     <script src="./js/jquery-ui.min.js"></script> -->
<!--     <script src="./js/mobile-menu.js"></script> -->
<!--     <script src="./js/chosen.min.js"></script> -->
<!--     <script src="./js/slick.js"></script> -->
<!--     <script src="./js/jquery.elevateZoom.min.js"></script> -->
<!--     <script src="./js/jquery.actual.min.js"></script> -->
<!--     <script src="./js/fancybox/source/jquery.fancybox.js"></script> -->
<!--     <script src="./js/lightbox.min.js"></script> -->
<!--     <script src="./js/owl.thumbs.min.js"></script> -->
<!--     <script src="./js/jquery.scrollbar.min.js"></script> -->
<!--     <script src='http://www.google.cn/maps/api/js?key=AIzaSyC3nDHy1dARR-Pa_2jjPCjvsOR4bcILYsM'></script> -->
<!--     <script src="./js/frontend-plugin.js"></script> -->
</body>
</html>