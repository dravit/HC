<div id="top">
    <nav class="navbar navbar-inverse navbar-static-top">
        <div class="container-fluid">
            <div class="topnav">
                <div class="btn-group">
                    <a data-placement="bottom" data-original-title="Fullscreen" data-toggle="tooltip"
                       class="btn btn-default btn-sm" id="toggleFullScreen">
                        <i class="glyphicon glyphicon-fullscreen"></i>
                    </a>
                </div>
                <div class="btn-group">
                    <a href="${pageContext.request.contextPath}/logout" data-toggle="tooltip" data-original-title="Logout" data-placement="bottom"
                       class="btn btn-metis-1 btn-sm">
                        <i class="fa fa-power-off"></i>
                    </a>
                </div>
                <%--<div class="btn-group">
                    <a data-placement="bottom" data-original-title="Show / Hide Left" data-toggle="tooltip"
                       class="btn btn-primary btn-sm toggle-left" id="menu-toggle">
                        <i class="fa fa-bars"></i>
                    </a>
                    &lt;%&ndash;<a data-placement="bottom" data-original-title="Show / Hide Right" data-toggle="tooltip"
                       class="btn btn-default btn-sm toggle-right">
                        <span class="glyphicon glyphicon-comment"></span>
                    </a>&ndash;%&gt;
                </div>--%>
            </div>
            <!-- Brand and toggle get grouped for better mobile display -->
            <header class="navbar-header">
                <a href="${pageContext.request.contextPath}" class="navbar-brand"><img src="<c:url value='/static/images/logo.png'/>" alt="Hunting Cube"></a>
            </header>
            <div class="collapse navbar-collapse navbar-ex1-collapse">
                <!-- .nav -->
                <ul class="nav navbar-nav">
                    <li id="data"><a href="<c:url value='/dataList' />">Data</a></li>
                    <li id="adminPage" class="dropdown active">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">Admin<b class="caret"></b> </a>
                        <ul class="dropdown-menu">
                            <sec:authorize access="hasRole('ADMIN')">
                                <li><a href="<c:url value='/newuser' />">Register New User</a></li>
                                <li><a href="<c:url value='/addResourceExcel' />">Upload Excel</a></li>
                            </sec:authorize>
                            <li><a href="<c:url value='/clientList' />">Clients</a></li>
                            <li><a href="<c:url value='/streamList' />">Streams</a></li>
                            <li><a href="<c:url value='/list' />">Users List</a></li>
                            <li><a href="<c:url value='/edit-user-${userSSOId}' />">Edit Details</a></li>
                        </ul>
                    </li>

                    <%--<li><a href="file.html">File Manager</a></li>
                    <li class='dropdown '>
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                            Form Elements <b class="caret"></b>
                        </a>
                        <ul class="dropdown-menu">
                            <li><a href="form-general.html">General</a></li>
                            <li><a href="form-validation.html">Validation</a></li>
                            <li><a href="form-wysiwyg.html">WYSIWYG</a></li>
                            <li><a href="form-wizard.html">Wizard &amp; File Upload</a></li>
                        </ul>
                    </li>--%>
                </ul>
                <!-- /.nav -->
            </div>
        </div>
        <!-- /.container-fluid -->
    </nav>
</div>
<%--
<script>
    $(document).bind("contextmenu",function(e) {
        e.preventDefault();
    });
    $(document).keydown(function(e){
        if(e.which === 123){
            return false;
        }
    });
</script>--%>
