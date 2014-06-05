<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>App</title>
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <!-- style -->
        <link rel="stylesheet" href="/css/ext/pure/pure.css"/>
        <link rel="stylesheet" href="/css/phoenix.css"/>

        <!-- style app -->
        <link rel="stylesheet" href="/learningswitch/web/css/simple.css"/>

        <!-- scripts -->
        <script data-main="/learningswitch/web/js/main" src="/js/ext/requirejs/require.js"></script>

    </head>
    <body>

        <h1> Learning Switch </h1>

        <h3> Select node </h3>
        <select id="nodeselect"> </select>
        <button id="refreshNodeList" class="pure-button
            button-success">Refresh Node List</button>

        <div id="mactablediv"></div>
        <!-- full-width outer box  -->
        <div class="pure-g">
            <div class="pure-u-1 pure-u-md-1-1"> 

                <!-- split-width inner box for heaer  -->
                <div class="pure-g">
                    <div class="pure-u-4-5">
                        <h2> Switch Mac Table </h2>
                    </div>

                    <div class="pure-u-1-5">
                        <button id="refreshMacTable" class="pure-button button-success">Refresh</button>
                    </div>
                </div> <!-- inner box ends -->

                <table class="pure-table" id="mactable" style="">
                    <thead>
                        <tr>
                            <th>MAC</th>
                            <th>Nodeconnector</th>
                        </tr>
                    </thead>
                    <tbody>
                    </tbody>
                </table>

            </div> 
        </div> <!-- outer box ends -->


        <p /><p />



        <div id="flowtablediv"></div>
        <div class="pure-g">
            <div class="pure-u-1 pure-u-md-4-5">
                <h2>Flow tables</h2>
            </div>
            <div class="pure-u-1 pure-u-md-1-5">
                <button id="refreshFlowTable" class="pure-button button-success">Refresh</button>
            </div>

            <table class="pure-table" id="flowtable" style="">
                <thead>
                    <tr>
                        <th>Flow</th>
                    </tr>
                </thead>
                <tbody>
                </tbody>
            </table>
        </div>



    </body>
</html>
