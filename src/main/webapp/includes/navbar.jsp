<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container">
        <a class="navbar-brand" href="<%=request.getContextPath()%>/list">Pharmacy on Duty</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse"
                data-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false"
                aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">

            <ul class="navbar-nav ml-auto">
                <li class="nav-item"><a class="nav-link" href="log-out">Logout</a></li>
            </ul>
            <ul class="navbar-nav">
                <li><a href="<%=request.getContextPath()%>/logList"
                       class="nav-link">User Log List</a></li>
            </ul>
        </div>
    </div>
</nav>
