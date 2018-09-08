<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html>
<head>
    <title>Home</title>
    <link href="<%=basePath%>/jsp/css/bootstrap.css" rel="stylesheet" type="text/css" media="all"/>
    <!-- Custom Theme files -->
    <script src="<%=basePath%>/jsp/js/jquery.min.js"></script>
    <!--theme-style-->
    <link href="<%=basePath%>/jsp/css/style.css" rel="stylesheet" type="text/css" media="all"/>
    <!--//theme-style-->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="keywords" content=""/>
    <script type="application/x-javascript"> addEventListener("load", function () {
        setTimeout(hideURLbar, 0);
    }, false);

    function hideURLbar() {
        window.scrollTo(0, 1);
    } </script>
    <script type="text/javascript" src="<%=basePath%>/jsp/js/move-top.js"></script>
    <script type="text/javascript" src="<%=basePath%>/jsp/js/easing.js"></script>
    <script type="text/javascript">
        jQuery(document).ready(function ($) {
            $(".scroll").click(function (event) {
                event.preventDefault();
                $('html,body').animate({scrollTop: $(this.hash).offset().top}, 1000);
            });
        });
    </script>

    <script type="text/javascript" src="<%=basePath%>/jsp/js/numscroller-1.0.js"></script>
    <link href="<%=basePath%>/jsp/css/index.css" rel="stylesheet" type="text/css" media="all"/>
    <link href='https://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700,800' rel='stylesheet' type='text/css'>
    <link href='https://fonts.googleapis.com/css?family=Noto+Sans:400,700' rel='stylesheet' type='text/css'>

</head>
<body>
<!--header-->
<div class="header" id="header">
    <div class="head">
        <div class="nav-top">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                        data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <div class="navbar-brand logo ">
                    <h1 class="animated wow pulse" data-wow-delay=".5s">
                        <a href="<%=basePath%>login.jsp">登录 </a></h1>
                </div>
                <div class="clearfix"></div>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse nav-wil links" id="bs-example-navbar-collapse-1">
                <div class="fill">
                    <ul class="nav navbar-nav ">
                        <li><a href="index.jsp" class="scroll active" data-hover="Home">首页</a></li>
                        <li><a href="#services" class="scroll" data-hover="About">服务简介</a></li>
                        <li><a href="#success" class="scroll" data-hover="Gallery">教学成果</a></li>
                        <li><a href="#testimonials" class="scroll" data-hover="Gallery">师资力量</a></li>
                        <li><a href="#gallery" class="scroll" data-hover="Gallery">深动课堂</a></li>
                        <li><a href="#team" class="scroll" data-hover="Codes">专业团队 </a></li>
                        <li><a href="#contact" class="scroll" data-hover="Contact">联系我们</a></li>
                    </ul>
                    <div class="clearfix"></div>
                </div>
            </div><!-- /.navbar-collapse -->
        </div>

        <!-- start search-->

        <div class="search-box">
            <div id="sb-search" class="sb-search">
                <form>
                    <input class="sb-search-input" placeholder="Enter your search term..." type="search" name="search"
                           id="search">
                    <input class="sb-search-submit" type="submit" value="">
                    <span class="sb-icon-search"> </span>
                </form>
            </div>
        </div>
        <!----search-scripts---->
        <script src="js/classie.js"></script>
        <script src="js/uisearch.js"></script>
        <script>
            new UISearch(document.getElementById('sb-search'));
        </script>
        <!----//search-scripts---->


        <div class="clearfix"></div>
    </div>

</div>
<!--content-->
<script src="<%=basePath%>/jsp/js/responsiveslides.min.js"></script>
<script>
    // You can also use "$(window).load(function() {"
    $(function () {
        $("#slider2").responsiveSlides({
            auto: true,
            pager: true,
            speed: 300,
            namespace: "callbacks",
        });
    });
</script>

<div class="banner">
    <div class="container">

        <!--banner-->
        <div class="slider">
            <ul class="rslides" id="slider2">
                <li>
                    <div class="banner-text"><b>[</b>
                        <h2> 参赛项目 <span>HiyTunes-慧通</span></h2>
                        <h6>教育机构推广与课堂管理平台</h6><b class="right-a">]</b>
                    </div>
                </li>
                <li>
                    <div class="banner-text"><b>[</b>

                        <h2> 这里是广告位 <span> 这里是广告位</span></h2>
                        <h6>这里是广告位</h6><b class="right-a">]</b>
                    </div>
                </li>
                <li>
                    <div class="banner-text"><b>[</b>
                        <h2> 参赛项目 <span>HiyTunes-慧通</span></h2>
                        <h6>教育机构推广与课堂管理平台</h6><b class="right-a">]</b>
                    </div>
                </li>
            </ul>
        </div>

    </div>
</div>
<!--content-->
<div class="service" id="services">
    <div class="container">
        <div class="ser-top">
            <h3>服务项目</h3>
            <div class="ser-t">
                <b></b>
                <b class="line"></b>
            </div>
            <p> 慧通结合了线上线下两种形式的优点，将一些零散的、孤立的教育资源整合起来，将传统的线下模式与应时的线上模式相结合，使其信息化
                、系统化，方便各个级别的学生根据自己的需要选择正确合理的教育资源，以达到学员们各取所需。
            </p>

        </div>
        <div class="service-head">
            <div class="col-md-5 ser-head">

                <h5>慧通是什么 ？ </h5>
                <img src="<%=basePath%>/jsp/images/aa.jpg" class="img-responsive" alt="">
                <p>
                    高效在线课堂管理平台，专门针对教学场景设计的交互式课堂管理工具,课件云同步,思维导图,超级分类,游戏化互动等等,只需简单
                    的操作就能让您的知识点跃然呈现。可以广泛被用于国内的教育机构或组织中，为老师及学生提供便捷的班级管理、作业在线批改、
                    成绩汇总分析、课件分享、在线讨论等服务。</p>
                <ul>
                    <li><i class="glyphicon glyphicon-edit"></i>培训机构：与我们联系过后代理开通，可进行机构
                        的注册及运行等，具有一定管理权限，统筹管理整个培训机构事宜。
                    </li>
                    <li><i class="glyphicon glyphicon-edit"></i>培训机构讲师：课前做好授课准备，课上做好课堂管理及考勤，课后做好教学反馈。</li>
                    <li><i class="glyphicon glyphicon-edit"></i>学员：参加学习的儿童以及在校学生。
                    </li>
                    <li><i class="glyphicon glyphicon-edit"></i>家长：学员的家长，可通过系统查看了解学员的课堂表现及学习情况。
                    </li>
                </ul>

            </div>
            <div class="col-md-7 ser-head1">
                <div class="col-md-6 ser-grid ">
                    <div class=" hi-icon-effect-7 hi-icon-effect-7a">
                        <i class="glyphicon glyphicon-piggy-bank hi-icon"> </i>
                    </div>
                    <h3>创新，“新”在哪？</h3>
                    <p>
                        慧通，将会使学前和中小学课堂迈向信息化！多媒体辅助授课、信息化课堂，这些在大学课堂中已经不再陌生。然而在幼儿与中小学生教育方面，至今依然沿用着传统的教学模式、单一的授课方式（黑板讲课）、原始的激励手段（奖状、小红花）。</p>
                </div>

                <div class="col-md-6 ser-grid ">
                    <div class=" hi-icon-effect-7 hi-icon-effect-7a">
                        <i class="glyphicon glyphicon-user hi-icon "> </i>
                    </div>
                    <h3>课堂学习氛围</h3>
                    <p>淘汰传统的小红花奖励机制，采用积分制，更清晰地记录每位学生的课堂表现。每位学生都会佩戴手表，通过它来进行课程签到
                        、积分交互、课堂抢答，通过加分扣分这种方式来提高孩子们学习的积极性，最终形成一个良好的融洽的课堂氛围。</p>
                </div>

                <div class="col-md-6 ser-grid ">
                    <span></span>
                    <div class=" hi-icon-effect-7 hi-icon-effect-7a">
                        <i class="glyphicon glyphicon-leaf hi-icon "> </i>
                    </div>
                    <h3>机构和教师</h3>
                    <p>每一个培训机构，都可以注册教师、班级和学生，教师也可以作为个体独立的开办培训课程。每次上课，教师都可以通过APP课
                        前发布学习内容、课上做好考勤，课后记录学生的学习和发布作业，这些都是作为一个学习管理软件所必备的功能。</p>

                </div>
                <div class="col-md-6 ser-grid ">
                    <div class=" hi-icon-effect-7 hi-icon-effect-7a">
                        <i class="glyphicon glyphicon-cog hi-icon "> </i>
                    </div>

                    <h3>家长和孩子</h3>
                    <p>家长通过扫描一下二维码就可以看到孩子的积分情况以及在班级里面的排名，这样家长可以更直观的了解孩子的学习状况，及时
                        与老师沟通，让家长也参与进孩子的学习中，不再对孩子的学习两眼一抹黑。</p>
                </div>

                <div class="clearfix"></div>
            </div>
            <div class="clearfix"></div>
        </div>
    </div>
</div>

<!---statistics--->
<div class="statistics" id="success">
    <div class="container">
        <div class="ser-top ser-top1">
            <h3>教学成果</h3>

            <div class="ser-t">
                <b></b>
                <b class="line"></b>
            </div>
        </div>
        <div class="statistics-grids">
            <div class="col-md-3 statistics-grid">
                <div class="statistics-text">
                    <div class='numscroller numscroller-big-bottom' data-slno='1' data-min='0'
                         data-delay='.5' data-increment="100">102
                    </div>
                    <h5>教育机构</h5>
                    <div class="sar-t">
                        <b></b>
                        <b class="line-1"></b>
                    </div>
                </div>
            </div>
            <div class="col-md-3 statistics-grid">
                <div class="statistics-text">
                    <div class='numscroller numscroller-big-bottom' data-slno='1' data-min='0'
                         data-delay='.5' data-increment="100">2812
                    </div>
                    <h5>签约讲师</h5>
                    <div class="sar-t">
                        <b></b>
                        <b class="line-1"></b>
                    </div>
                </div>
            </div>
            <div class="col-md-3 statistics-grid">
                <div class="statistics-text">
                    <div class='numscroller numscroller-big-bottom' data-slno='1' data-min='0'
                         data-delay='.5' data-increment="100">91430
                    </div>
                    <h5>芊芊学子</h5>
                    <div class="sar-t">
                        <b></b>
                        <b class="line-1"></b>
                    </div>
                </div>
            </div>
            <div class="col-md-3 statistics-grid">
                <div class="statistics-text">
                    <div class='numscroller numscroller-big-bottom' data-slno='1' data-min='0'
                         data-delay='.5' data-increment="100">而你
                    </div>
                    <h5>还在观望</h5>
                    <div class="sar-t">
                        <b></b>
                        <b class="line-1"></b>
                    </div>
                </div>
            </div>
            <div class="clearfix"></div>
        </div>
    </div>
</div>
<!---statistics--->
<div class="tlinks">Collect from <a href="http://www.cssmoban.com/">手机网站模板</a></div>
<!--gallery-->
<div class="gallery" id="gallery">
    <div class="container">
        <div class="ser-top ga-top">
            <h3>深动课堂</h3>
            <div class="ser-t">
                <b></b>
                <b class="line"></b>
            </div>
            <p> 在课程推广的过程中，家长和学员们都希望可以找到一种适合自己的课堂体验。只有孩子的学习兴趣上去了，孩子才会学的有乐趣有动力。</p>

        </div>

        <ul class="simplefilter">
            <li class="active" data-filter="all">所有</li>
            <li data-filter="1">幼儿课堂</li>
            <li data-filter="2">小学课堂</li>
            <li data-filter="3">初中课堂</li>
            <li data-filter="4">高中课堂</li>
            <li data-filter="5">高三课堂</li>
        </ul>

        <div class="filtr-container">
            <div class="  filtr-item gallery-t" data-category="1, 5" data-sort="Busy streets">

                <a href="<%=basePath%>/jsp/images/ga.jpg" rel="title" class="b-link-stripe b-animate-go  thickbox">

                    <figure>
                        <img src="<%=basePath%>/jsp/images/ga.jpg" class="img-responsive" alt=" "/>
                        <figcaption>
                            <h3>Prevailing</h3>
                            <p>
                                Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod
                                tempor incididunt ut labore et dolore magna aliqua.
                            </p>
                        </figcaption>
                    </figure>
                </a>

            </div>
            <div class=" filtr-item" data-category="2, 5" data-sort="Luminous night">
                <a href="<%=basePath%>/jsp/images/ga1.jpg" rel="title" class="b-link-stripe b-animate-go  thickbox">

                    <figure>
                        <img src="<%=basePath%>/jsp/images/ga1.jpg" class="img-responsive" alt=" "/>
                        <figcaption>
                            <h3>Prevailing</h3>
                            <p>
                                Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod
                                tempor incididunt ut labore et dolore magna aliqua.
                            </p>
                        </figcaption>
                    </figure>
                </a>

            </div>
            <div class=" filtr-item" data-category="1, 4" data-sort="City wonders">
                <a href="<%=basePath%>/jsp/images/ga2.jpg" rel="title" class="b-link-stripe b-animate-go  thickbox">

                    <figure>
                        <img src="<%=basePath%>/jsp/images/ga2.jpg" class="img-responsive" alt=" "/>
                        <figcaption>
                            <h3>Prevailing</h3>
                            <p>
                                Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod
                                tempor incididunt ut labore et dolore magna aliqua.
                            </p>
                        </figcaption>
                    </figure>
                </a>

            </div>
            <div class="  filtr-item" data-category="3" data-sort="In production">
                <a href="<%=basePath%>/jsp/images/ga3.jpg" rel="title" class="b-link-stripe b-animate-go  thickbox">

                    <figure>
                        <img src="<%=basePath%>/jsp/images/ga3.jpg" class="img-responsive" alt=" "/>
                        <figcaption>
                            <h3>Prevailing</h3>
                            <p>
                                Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod
                                tempor incididunt ut labore et dolore magna aliqua.
                            </p>
                        </figcaption>
                    </figure>
                </a>

            </div>
            <div class=" filtr-item" data-category="3, 4" data-sort="Industrial site">
                <a href="<%=basePath%>/jsp/images/ga4.jpg" rel="title" class="b-link-stripe b-animate-go  thickbox">

                    <figure>
                        <img src="<%=basePath%>/jsp/images/ga4.jpg" class="img-responsive" alt=" "/>
                        <figcaption>
                            <h3>Prevailing</h3>
                            <p>
                                Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod
                                tempor incididunt ut labore et dolore magna aliqua.
                            </p>
                        </figcaption>
                    </figure>
                </a>

            </div>
            <div class=" filtr-item" data-category="2, 4" data-sort="Peaceful lake">
                <a href="<%=basePath%>/jsp/images/ga5.jpg" rel="title" class="b-link-stripe b-animate-go  thickbox">

                    <figure>
                        <img src="<%=basePath%>/jsp/images/ga5.jpg" class="img-responsive" alt=" "/>
                        <figcaption>
                            <h3>Prevailing</h3>
                            <p>
                                Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod
                                tempor incididunt ut labore et dolore magna aliqua.
                            </p>
                        </figcaption>
                    </figure>
                </a>

            </div>
            <div class="  filtr-item" data-category="1, 5" data-sort="City lights">
                <a href="<%=basePath%>/jsp/images/ga6.jpg" rel="title" class="b-link-stripe b-animate-go  thickbox">

                    <figure>
                        <img src="<%=basePath%>/jsp/images/ga6.jpg" class="img-responsive" alt=" "/>
                        <figcaption>
                            <h3>Prevailing</h3>
                            <p>
                                Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod
                                tempor incididunt ut labore et dolore magna aliqua.
                            </p>
                        </figcaption>
                    </figure>
                </a>

            </div>
            <div class=" filtr-item" data-category="2, 4" data-sort="Dreamhouse">
                <a href="<%=basePath%>/jsp/images/ga7.jpg" rel="title" class="b-link-stripe b-animate-go  thickbox">

                    <figure>
                        <img src="<%=basePath%>/jsp/images/ga7.jpg" class="img-responsive" alt=" "/>
                        <figcaption>
                            <h3>Prevailing</h3>
                            <p>
                                Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod
                                tempor incididunt ut labore et dolore magna aliqua.
                            </p>
                        </figcaption>
                    </figure>
                </a>

            </div>
            <div class=" filtr-item" data-category="2, 4" data-sort="Dreamhouse">
                <a href="<%=basePath%>/jsp/images/ga8.jpg" rel="title" class="b-link-stripe b-animate-go  thickbox">

                    <figure>
                        <img src="<%=basePath%>/jsp/images/ga8.jpg" class="img-responsive" alt=" "/>
                        <figcaption>
                            <h3>Prevailing</h3>
                            <p>
                                Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod
                                tempor incididunt ut labore et dolore magna aliqua.
                            </p>
                        </figcaption>
                    </figure>
                </a>

            </div>

            <div class="clearfix"></div>
        </div>
    </div>
</div>

<!-- Include jQuery & Filterizr -->

<script src="<%=basePath%>/jsp/js/jquery.filterizr.js"></script>
<script src="<%=basePath%>/jsp/js/controls.js"></script>

<!-- Kick off Filterizr -->
<script type="text/javascript">
    $(function () {
        //Initialize filterizr with default options
        $('.filtr-container').filterizr();
    });
</script>
<!---->
<script src="js/jquery.chocolat.js"></script>
<link rel="stylesheet" href="css/chocolat.css" type="text/css" media="screen" charset="utf-8">
<!--light-box-files -->
<script type="text/javascript" charset="utf-8">
    $(function () {
        $('.filtr-item a').Chocolat();
    });
</script>

<!--//gallery-->
<link href="css/owl.carousel.css" rel="stylesheet">
<script src="js/owl.carousel.js"></script>

<!-- requried-jsfiles-for owl -->
<script>
    $(document).ready(function () {
        $("#owl-demo2").owlCarousel({
            items: 1,
            lazyLoad: false,
            autoPlay: true,
            navigation: false,
            navigationText: false,
            pagination: true,
        });
    });
</script>
<!-- //requried-jsfiles-for owl -->
<!-- start content_slider -->


<!---->

<div class="test" id="testimonials">
    <div class="container">
        <div class="ser-top ga-top">
            <h3>师资力量</h3>
            <div class="ser-t">
                <b></b>
                <b class="line"></b>
            </div>
            <p> 如果我们都去做自己能力做得到的事，我们真会叫自己大吃一惊。 善待你的爱好，别让它们为学习让路，要让它们替学习服务。</p>

        </div>
        <div class=" test-grid-1">
            <div class=" col-md-4 test-wrapper">
                <img src="<%=basePath%>/jsp/images/tt2.png" alt="" class="img-responsive">
            </div>
            <div class="col-md-8 test-grid1">
                <div id="owl-demo2" class="owl-carousel">
                    <div class=" test-grid">
                        <p>1、让理想随高考腾飞，让人生伴六月回味。<br/>
                            2、信心来自于实力，实力来自于勤奋。<br/>
                            3、谁笑到最后，谁笑得最美！<br/>
                            4、高考中没有失败，它带给每个人的深刻思考、刻骨铭心的经历和感受都是不可多得的财富。我们为理想而奋进的过程，其意义远大于未知的结果。</p>
                        <img src="<%=basePath%>/jsp/images/left-1.png" alt="" class="img-responsive">
                        <h4>艾里夫斯</h4>
                    </div>
                </div>

            </div>
            <div class="clearfix"></div>
        </div>

    </div>
</div>
</div>
<!---->
<div class="team" id="team">
    <div class="container">
        <div class="ser-top ga-top">
            <h3>专业团队</h3>
            <div class="ser-t">
                <b></b>
                <b class="line"></b>
            </div>

        </div>

        <div class="col-md-3 bottom-grid ">
            <div class="btm-right">
                <img src="<%=basePath%>/jsp/images/te.jpg" class="img-responsive" alt=" ">
                <div class="captn">
                    <h4>Victoria</h4>
                    <p>Ceo</p>
                    <ul class="captn2">
                        <li><a href="#" class="icon1"></a></li>
                        <li><a href="#" class="icon2"></a></li>
                        <li><a href="#" class="icon3"></a></li>
                    </ul>
                </div>
            </div>
        </div>
        <div class="col-md-3 bottom-grid ">
            <div class="btm-right">
                <img src="<%=basePath%>/jsp/images/te1.jpg" class="img-responsive" alt=" ">
                <div class="captn">

                    <h4>Adley</h4>
                    <p>Manager</p>
                    <ul class="captn2">
                        <li><a href="#" class="icon1"></a></li>
                        <li><a href="#" class="icon2"></a></li>
                        <li><a href="#" class="icon3"></a></li>
                    </ul>
                </div>
            </div>
        </div>
        <div class="col-md-3 bottom-grid ">
            <div class="btm-right">
                <img src="<%=basePath%>/jsp/images/te2.jpg" class="img-responsive" alt=" ">
                <div class="captn">

                    <h4>Immortal</h4>
                    <p>Web developer</p>
                    <ul class="captn2">
                        <li><a href="#" class="icon1"></a></li>
                        <li><a href="#" class="icon2"></a></li>
                        <li><a href="#" class="icon3"></a></li>
                    </ul>
                </div>
            </div>
        </div>
        <div class="col-md-3 bottom-grid ">
            <div class="btm-right">
                <img src="<%=basePath%>/jsp/images/te3.jpg" class="img-responsive" alt=" ">
                <div class="captn">

                    <h4>Warlike</h4>
                    <p>Web designer</p>
                    <ul class="captn2">
                        <li><a href="#" class="icon1"></a></li>
                        <li><a href="#" class="icon2"></a></li>
                        <li><a href="#" class="icon3"></a></li>
                    </ul>
                </div>
            </div>
        </div>
        <div class="clearfix"></div>
    </div>
</div>

<!--contact-->
<div class="map-top" id="contact">
    <div class="col-md-4 address">
        <div class="contact-grid1">


            <h4>Address</h4>
            <p>日照市东港区日照职业技术学院 <span>山东省</span></p>

        </div>

        <div class="contact-grid1">

            <h4>联系我们</h4>
            <p>17662079280<span>17662079280</span></p>

        </div>


        <div class="contact-grid1">

            <h4>邮箱</h4>
            <p><a href="mailto:info@example.com">915519331@qq.com</a><span><a href="mailto:info@example.com">915519331@qq.com</a></span>
            </p>

        </div>

    </div>
    <div class="clearfix"></div>
</div>
<div class="contact">
    <div class="container">
        <div class="ser-top ga-top">
            <h3>联系我们</h3>
            <div class="ser-t">
                <b></b>
                <b class="line"></b>
            </div>
            <p> 请在此处输入您的信息以及您的问题，稍后我们会以邮件的形式回复您！</p>

        </div>
        <div class="top-contact">
            <form action="#" method="post">
                <div class="col-md-6 grid-contact">
                    <div class="your-top">
                        <i class="glyphicon glyphicon-user"> </i>
                        <input type="text" placeholder="姓名" name="Name" required>
                        <div class="clearfix"></div>
                    </div>
                    <div class="your-top">
                        <i class="glyphicon glyphicon-envelope"> </i>
                        <input type="text" placeholder="邮箱" name="E-mail" required>
                        <div class="clearfix"></div>
                    </div>
                    <div class="your-top">
                        <i class="glyphicon glyphicon-link"> </i>
                        <input type="text" placeholder="个人网站" name="Website" required>
                        <div class="clearfix"></div>
                    </div>

                </div>
                <div class="col-md-6 grid-contact-in">
                    <textarea placeholder=" 请在此输入您的问题或咨询事宜" name="Message" required></textarea>
                    <input type="submit" value="提交">
                </div>
                <div class="clearfix"></div>
            </form>
        </div>
    </div>
</div>


<!--footer-->
<div class="copy">
    <p class="footer-grid">Copyright 1998 - 2018 Tencent. All Rights Reserved <a
            href="#" target="_blank" title=""> 粤公网安备 12345678901234号</a> - Collect from <a
            href="#" title="" target="_blank"> B2-6543210 </a></p>
    <ul class="captn2 footer-sc">
        <li><a href="#" class="icon1"></a></li>
        <li><a href="#" class="icon2"></a></li>
        <li><a href="#" class="icon3"></a></li>

</div>
</div>
<script type="text/javascript">
    $(document).ready(function () {
        /*
        var defaults = {
              containerID: 'toTop', // fading element id
            containerHoverID: 'toTopHover', // fading element hover id
            scrollSpeed: 1200,
            easingType: 'linear'
         };
        */

        $().UItoTop({easingType: 'easeOutQuart'});

    });
</script>
<a href="#" id="toTop" style="display: block;"> <span id="toTopHover" style="opacity: 1;"> </span></a>
</div>

<!-- for bootstrap working -->
<script src="<%=basePath%>/jsp/js/bootstrap.min.js"></script>
<!-- //for bootstrap working -->
</body>
</html>