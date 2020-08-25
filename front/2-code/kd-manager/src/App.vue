<template>
  <div id="app">
    <router-view />
  </div>
</template>

<script>
export default {
  name: 'App'
}
</script>
<style type="text/css">
	#app{
		overflow: hidden;
	}
	#app .main-container:nth-child(2){
		background-color: #eee;
	}
	.app-main{
		background-color: #fff;
	}
</style>