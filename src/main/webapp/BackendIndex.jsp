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
            <form class="search">
                <div class="search__inner"><input type="text" class="search__text" placeholder="搜尋..." size="30px"><i
                        class="zmdi zmdi-search search__helper" data-ma-action="search-close"></i></div>
            </form>
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

                <li class="dropdown hidden-xs-down"><a href="" data-toggle="dropdown"><i
                            class="zmdi zmdi-more-vert"></i></a>
                    <div class="dropdown-menu dropdown-menu-right">
                        <div class="dropdown-item theme-switch"> 頁面色彩選擇<div
                                class="btn-group btn-group-toggle btn-group--colors" data-toggle="buttons"><label
                                    class="btn bg-green active"><input type="radio" value="green" autocomplete="off"
                                        checked></label><label class="btn bg-blue"><input type="radio" value="blue"
                                        autocomplete="off"></label><label class="btn bg-red"><input type="radio"
                                        value="red" autocomplete="off"></label><label class="btn bg-orange"><input
                                        type="radio" value="orange" autocomplete="off"></label><label
                                    class="btn bg-teal"><input type="radio" value="teal" autocomplete="off"></label>
                                <div class="clearfix mt-2"></div><label class="btn bg-cyan"><input type="radio"
                                        value="cyan" autocomplete="off"></label><label class="btn bg-blue-grey"><input
                                        type="radio" value="blue-grey" autocomplete="off"></label><label
                                    class="btn bg-purple"><input type="radio" value="purple"
                                        autocomplete="off"></label><label class="btn bg-indigo"><input type="radio"
                                        value="indigo" autocomplete="off"></label><label class="btn bg-brown"><input
                                        type="radio" value="brown" autocomplete="off"></label>
                            </div>
                        </div>
                    </div>
                </li>
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

        <section class="content">
<header class="content__title">
                <h1>好學生後台管理首頁</h1>

            </header>
            <div class="row quick-stats">
                <div class="col-sm-6 col-md-3">
                    <div class="quick-stats__item bg-blue">
                        <div class="quick-stats__info">
                            <h2>987,459</h2><small>網頁瀏覽人次</small>
                        </div>
                        <div class="quick-stats__chart sparkline-bar-stats">6,4,8,6,5,6,7,8,3,5,9,5</div>
                    </div>
                </div>
                <div class="col-sm-6 col-md-3">
                    <div class="quick-stats__item bg-amber">
                        <div class="quick-stats__info">
                            <h2>356,785</h2><small>活動參加人次</small>
                        </div>
                        <div class="quick-stats__chart sparkline-bar-stats">4,7,6,2,5,3,8,6,6,4,8,6</div>
                    </div>
                </div>
                <div class="col-sm-6 col-md-3">
                    <div class="quick-stats__item bg-purple">
                        <div class="quick-stats__info">
                            <h2>$58,778K</h2><small>課程收入</small>
                        </div>
                        <div class="quick-stats__chart sparkline-bar-stats">9,4,6,5,6,4,5,7,9,3,6,5</div>
                    </div>
                </div>
                <div class="col-sm-6 col-md-3">
                    <div class="quick-stats__item bg-red">
                        <div class="quick-stats__info">
                            <h2>178,214</h2><small>專欄閱讀次數</small>
                        </div>
                        <div class="quick-stats__chart sparkline-bar-stats">5,6,3,9,7,5,4,6,5,6,4,9</div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-6">
                    <div class="card">
                        <div class="card-body">
                            <h4 class="card-title">課程購買人數</h4>
                            <h6 class="card-subtitle">numbers of course bought</h6>
                            <div class="flot-chart flot-curved-line"></div>
                            <div class="flot-chart-legends flot-chart-legends--curved"></div>
                        </div>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="card">
                        <div class="card-body">
                            <h4 class="card-title">試卷作答人數</h4>
                            <h6 class="card-subtitle">numbers of exam practiced</h6>
                            <div class="flot-chart flot-line"></div>
                            <div class="flot-chart-legends flot-chart-legends--line"></div>
                        </div>
                    </div>
                </div>
            </div>

            <footer class="footer hidden-xs-down">
                <p>Copyright © 2022 Good Student. All rights <a href="http://www.bootstrapmb.com/"
                        title="bootstrapmb">Reserved</a>.
                </p>

            </footer>


            
           
        </section>
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

</body>
</html>