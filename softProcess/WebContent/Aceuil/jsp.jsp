<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<meta name="author" content="www.frebsite.nl" />
		<meta name="viewport" content="width=device-width initial-scale=1.0 maximum-scale=1.0 user-scalable=yes" />

		<title>jQuery.dmenu demo</title>

		<link rel="stylesheet" href="css/demo.css" />
		<link rel="stylesheet" href="jquery.dmenu.css" />
		<style>
			.logo
			{
				width: 80px;
				margin-top: 10px;
			}
			.header-menu
			{
				background: #cdcecf;
			}
			.dm-menu
			{
				--dm-bg: #343536;
				--dm-color: #fff;
				--dm-item-current-bg: #fff;
				--dm-item-current-color: #333;
				--dm-item-hover-bg: #54b4eb;
				--dm-item-hover-color: #fff;
			}
			.header-slider
			{
				margin-top: 20px;
			}
		</style>

		<script src="https://use.fontawesome.com/releases/v5.0.6/js/all.js"></script>
		<script src="http://code.jquery.com/jquery-3.2.1.min.js"></script>
		<script src="jquery.dmenu.js"></script>
	</head>
	<body>

	
						<nav id="menuProcess">
							<a href="#" style="background-image: url(img/logo.png)"></a>
							<ul>
								<li>
									<a href="#">Lorem</a>
									<ul>
										<li class="SubSubmenuInline">
											<ul>
												<li>
													<a href="#">Link (with inline submenu)</a>
													<ul>
														<li><a href="#">Lorem ipsum dolor</a></li>
														<li><a href="#">Dolor sit amet</a></li>
														<li><a href="#">Lorem ipsum dolor</a></li>
														<li><a href="#">Dolor sit amet</a></li>
													</ul>
												</li>
											</ul>
										</li>
									</ul>
								</li>
								<li class="AlignRight">
									<a href="#">Align right</a>
									<ul>
										<li><a href="#">Link</a></li>
										<li><a href="#">Link</a></li>
									</ul>
								</li>
							</ul>
						</nav>
						<script>
							$(function() {
								$(window).resize();
							});
							$('#menuProcess').dmenu({
								menu 	: {
									border			: false,
									logo			: false,
									align			: false
								},
								item	: {
									bg				: true,
									border 			: false,
									subindicator	: true,

									fit			: [
										{
											items 		: null,
											fitter 		: 'icon-hide',
											order		: 'all'
										},
										{
											items 		: null,
											fitter 		: 'icon-only',
											order		: 'all'
										},
										{
											items 		: ':not(.dm-item_align-right)',
											fitter 		: 'submenu',
											order		: 'rtl'
										},
										{
											items 		: ':not(.dm-item_align-right)',
											fitter 		: 'hide',
											order		: 'rtl'
										}
									]
								},
								submenu	: {
									arrow			: false,
									border			: true,
									shadow			: true
								},
								subitem	: {
									bg				: true,
									border			: false
								}

							});
						</script>
	</body>
</html>