<template>
<div>
	<div id="map" ref="map">
		
			<div id="markers" v-for="marker in $store.state.potholes" :key="marker.id"> 
				<map-marker  :lat="marker.latitude" :lng="marker.longitude"></map-marker>

				<map-info-window v-if="marker.severity == 0" class='zero' :lat="marker.latitude + .00001" :lng="marker.longitude">
					<img src="../../assets/pothole_sample.jpg" width="100%" height="160"/><br/>
					<img src="data:image/jpg;base64, IlxceGZmZDhmZmUwMDAxMDRhNDY0OTQ2MDAwMTAxMDAwMDAxMDAwMTAwMDBmZmRiMDA4NDAwMGEwNjA4MTUxNTEzMTcxNTE1MTYxODE4MTcxOTFhMWYxYTFiMWExYTFhMjExYzFkMWExYjIzMWMxZjFmMjAyMDFmMWIyNjIxMmIyMzFiMjMyODIwMTkxOTI1MzUyNDI4MmMyZTMyMzIzMjFkMjEzNzNjMzczMDNiMmIzMTMyMmUwMTBiMGIwYjBmMGUwZjE5MTExMTFjMzEyODFmMjgzMTMxMzEzMTMxMzEzMTMxMzEzMTMxMzEzMTMxMzEzMTMxMzEzMTMxMzEzMTMxMzEzMTMxMzEzMTMxMzEzMTMxMzEzMTMxMzEzMTMxMzEzMTMxMzEzMTMxMzEzMTMxMzEzMTMxZmZjMDAwMTEwODAwYTIwMTM3MDMwMTIyMDAwMjExMDEwMzExMDFmZmM0MDAxYjAwMDAwMjAzMDEwMTAxMDAwMDAwMDAwMDAwMDAwMDAwMDAwMzA0MDAwMjA1MDEwNjA3ZmZjNDAwM2UxMDAwMDIwMTAyMDQwNDA0MDQwNDA2MDEwMzA0MDMwMTAwMDEwMjExMDMyMTAwMTIzMTQxMDQwNTIyNTExMzYxNzE4MTA2MzI5MWExNDJiMWMxZjAyMzUyNjJkMWUxZjExNDA3MTUzMzI0NzI4MmMyNTNiMmIzMTZmZmM0MDAxNzAxMDEwMTAxMDEwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAxMDIwM2ZmYzQwMDIwMTEwMTAxMDEwMDAyMDIwMjAzMDEwMDAwMDAwMDAwMDAwMDAwMDExMTAyMjExMjQxMDMzMTUxNjE4MTEzZmZkYTAwMGMwMzAxMDAwMjExMDMxMTAwM2YwMGM5YWJjYzJiYjU5NjljMGY2MWY1ZDdlZjgxMmYwN2M0M2RjYzJmZGNmZjAwNmM3YTlhYmMyMjUzM2Q0NTQ2OTMxMmQxZWUyYzBlYmY2YzcyYWQ0YTBhYTQ1NGE5MjMyZTgxNDI5MzYyNjM1YjkyMDFiNzk2OWRiODdmYjRmNWRmZjFkN2MyZmI3OWJhN2YwZmI5ODJlY2U2NGZhN2RhNDdkYTcwZTcwYmYwZDIwM2Q1OTY3NzhiZmJlODdlOTM4ZDQxYzdkMzQ1MTE0YzkwMzQzOWFkM2RhMDEwMTYzNjlmYmUyNzExY2M1Y2IyOTQ1NTVjYWRiMmM1YTA1YzliZGJjZjE3Y2Y5ZGZhZTI3OGYxOWY3NWRlMTM5MzIyMGIyMTIzNjMwN2NjZWE3MGNkMmUxMTQyYzkyOTk1YWRkNmUyMDhkMzQxZjkxZGYwOTNmMTU1MWEzM2IzOTkxMzZiMGY2Y2E2YzdlZjZjMmY0Mzg1MzFkMzlhZjM3MjU4MWI5OThkNDE4ODI3NTliYzViYmJjN2U0YmY3NjQzNzg0NmNkN2FkNGQwNGU3ZGE1ODIyMWQyZGU1YWY5NjAzNTc4ZDRiNjVlYmYzMjRlOTMzNjAwYzY5NzkxMzMwNzBiZDNlNWM1OGU4Mjc0ZDYwOTgzZGVlNmRkZmQzZDcwZjUzZTQ4MGQ5Y2VhMjNlNmQwNWY2ZjUzZTc4NGY4YWRmYmI0YmYyN2UyMDVmZjAwNzEzOTQ4MGE4MDA4YjQ1Y2ViYTU4YzVhMmYxODAyZjExNTFjY2I0YjExYTQwODgxNzhkZjVmMzE3ZmI4YzZkZjBkY2E1MDQ5MjY2ZmJkODgzZGZlZjNlZjg3NjhmMDg4OWIwMTA3YmY3M2ZlNzFhZTNmMGYwOWU5MmZjOWNhYmNlZDFhNGVkNjJiNmRlYzZmM2RmNjZkMjdmNGJlMWJhN2NiODliNDc5NGU5ZmE3ZGYxYmFlZWEzZjcxZmJkNzAxNmUyMDEzMDBkZmNiMWQyNzE5MThkMjU0Zjk2MDk5NjMwN2Y3YjdmNmMzMzRiODFhNjNmYjYyZmUyNzYxZmJmNmM0NTA2MmY2ZjVkYjFhNDVlMTU3NDhmNWI3ZmJjNzQ1NjEzNmZhZmQzN2MwNDkwNDhkZmViNmZkMDYzYTUwOTA2MjAxMzY5YmNmZDJkMWZiYjYwMGJlMjMzNjgwZmI3ZjljMDVlYWM2YWRmNTIyN2ZjZTJjZWE5YTFlZmJmZTVmN2MwNTM4OTQxMzYxZGM1YjI4ZGI0OTg5ZDQ1ZTQ4ZmI2MDNhYWMzNGRlMjY2MjNmZGUyYWQ1NDAxYWMwZmJmYjRmZjZjMjk1Yjk5MjkwNDJiMjllOTZiMDYxMTIwNDk5NmJkYWUyZmIwM2E2YjhhYTcwOGNlMDk2Mjg0ZWNjNDlkM2QzZWQ4MDI1NGUyOTIyZTY3NjkzNzFlZjFhN2ZhYzc2OGYxMzI0NDA2MDI0ODMxMDI3ZGI1OGY2MWE2MTZlMjJhZDJhNDFiMzU0MDRhYTkzOTQ2NTA0ZGUwY2NlOWY2ZDc3YzEyOWQ0MmYwMjk1MjIzMzA5MDRjYzRkOGY2YmRiNzEzYTFlZDg4MmY1NmIxZWMyMmRiNDhlZjMzYWM0NWVjMzZmNWM0Y2I5MDljZDUxNTZkMjMyOWZlZjZiYzg4MTgwOWUyMmE4ODg2ZjEwOTI3YTU0ODUwNTQ0NGNiMmQzMjRkY2MyZGEwODIyZTc1YzFiODRlNWI1MWRiMzFhNDEwY2ZjZTRiNGM4ZTkzMjM1NmI0OGQ3ZmFiNjE4MDU3ZmU1NTMwNDM0YjEzMTIyN2E1NzJkYWUyMGU1YjdhY2VkNjkzODg4NTlhNDk1ZTk2OTIzMmIwMzNkMzEwMjBjOWI1ZTQ1ZmE3ZDMxYjZkZjBkMjMxNTJjYzQ1YzkyNjM1NTIyMzI2NTNhMDAwOGJjMGJhZGY1MjBlY2YwYmMxNTNhN2EyZmQ4NWVmM2VmODBmMmZjMDcwNGU0MTY0YTIyOWU2MDY0YzVmN2JjMGVhYmMwZDZjNjM0YzZhZjBiYzhjZWFlYzNkMDAyM2VhMzM1YmVmYWNlYjhkYTk4YjQ3ZWZlOTg4NDYwMDE0NzgyNDRkYmRmZmNmYWUwOTFkYWQ4OTBhMmYzM2VmMzhhM2Q0OWM1NDc1ODYwMmM0MGM3NDg5ZDMwMzExNjMzNmY1ZmExMWZiZGYwMWMyN2RiMTUwYzNkYjFkYWFlMDZlNjcwOGYxMWM1MjgxMjI2NmRmODBiNWE0MDNhNDQ2ZjdkMDZhNzRjMTRlNTRhODA2MTY2ZTI4NWU0ZTk3YzY1ZjE5Y2MyOWFkYzhmZTU1OGQwYzkzMDM1MzE3OWRiMTk4ZmM3NTEwMDkyMDg1MjRjY2FlNjEyNGNjOTM3ODk5MjZmMDA3OTYyMGQ1ZTJiOWFhYTg0MjE2ZWRhMDgyMGM2YTQxZTllOTgxMTYzMTdiNjE2YWRjYzljOTBhMDVmMjliODg4ZDg5YjE2MTNiMGY3MTdjNjdkMGE3MjJhMDgyYzVlM2E5NmMwODgxYTExMDJkZDIzMmNjY2ZlMmJkOThhOTQ1OWEwYWQzYTg1YjMwODAzNzE5YzAyNjU4ZTUwMzVkNjBjNjllNjAyYThhNGIxYTYxOWIzMzAyZDMwMjE0MDYxNjBkMTAyZjMwYTY0ZGRhMzdjNGM2ZTcwZGMxMjUyNGNmMzBiNjhlYTgwYTA4ZDA1YWMyZjg5ODBmMTQyOTMxMzE3MzYxYWQ4MTAwY2VkMDQ0ZGI0YzFiODVlNWYyNDhjYTY1YTI2ZGE0NGRlZmE4MTIyMDkxZjc5YzdhN2E1YzJhYWM5NTUxNzFhNWNlOWQ4ZTliOWZkOGMxNjk5MDA0NDZkYTVhZjg3OGFlYjBlOGYyNzc4MzI3Y2E0YzVmY2FlMjc0ZmRlZjg2Zjg3ZTUwMGNmNTZkMzAzZWQ3YjdlZmQ3MWEyMWEwNDZkYTA5ZWRkYmY3MzhlZTdiMDI0MWI2OThiODlhMGQwZTViNGQ0NGU1MTdkN2Q3NzljMzZiNDkwNjgzZTlmYmJlMDI5NTRmZGJmOTdkNzRkYmI2M2EwOWQwODkwNzVkYmVjNzAwYzE3NTFkZmY3ZTU4OWUyYWM1ODE4ZmYwMDVmZGYwMGIwM2U2NDdlZWRlZDg4MWQyNDAwNDRmZWZiNjA4M2U3NjNmOTdhOGY0ZDBlMzk1MjliNmE1OGNmNzM4MTNiYjAxZDJhMGRlMGRlMmRkZmRhNjZmZGIxNWE5NTJkOTljOTIwMTNmMmNkZTA3ZWM0OThiOGMwMTQyYzBiOWZiN2U1ZmUzMWRhNDY2MjAxODM3ZmRmNmMwMmFmMTIxNGNlNWVhZDE2NjA2NjlkMDY2OTMxMjU3ZWUzMDBlMzM4ODYyMDBjOWQyYzYwOTI0YWM2ZGFjODIyNGM2OWY2YzE0ZWQ0NzIyNjVhZGQ4MDkzZmU3ZTk4MDU0ZTMyOTI5OTJkNjAyZTYyNDdmOGZmMDAwNzA4NTRlMjhjYWU3MmVhNWI0MTAyNmRhZTkzMzNhNGU5MWRhZDJkMTBhMjA4ZWE5ZmNhZTY2NDhiODEzODgwOTUzODlhODRjMGE2NjJmZDc2MTFlZDNkYjAxY2VkZjhhMDE5MzBiMjNhODg5OGJlOTA3MmM4ZDIzMGIwZTM5MDI4NzYwNDRlNjIyMTU4ZGE2MGMxYjAyMGMyZGZkZWY4YzkzY2UyYTY3YThmNGM0YTQ1ODAwN2E2N2E4OWI0YWJjOWJlYmE3YWRkYWI4ZjQyNzJkZTZlMDMwMjI2MzVkMDY1MzYxYjA4MzIyZTdiZTMzYWE3MTU0ZmZhYjMwOTAxNjliMmM0Yzg4Y2IxMzk4MDhkYzZkMDA1ZTMxOTljNGQwYWU0YjFhODJhMTAyMjQ4NTI2NjQ0NmM5OTYyZTM3ZDg2YTIwZTI5NDU0MGI2NTIwOTQ4MWExMjQ4ODI1ODJlNWQ3NDY4MDJkZGM2MjY5OGQ3MWM1OTczZTFhNTM3NDYxOWNjYmExOGIxMTI0OTI1NmY5NTg4OGJkZGI0ODE3MzcxMWMxY2MxN2FjZWMwODAwODA2MDRjOTI0ZmNjMDBkMDgxMzdlYWYyMDMxOTljMGYwOGI1MTg0ZDQ3OTI2NTdjNDEzZDMyMzc5M2IwM2QyMDhkMDYzZDY3MjllNTM5NTQxMGExNjIwMWU5OTFiY2MwOWI3Y2M2ZTY3NTNkZWUxOTNjMGYyZDZhODI1NDEwMDI5ZjlhMGNjMzE4NmQ0OTA3NTI3NDljYzRkZWQ4ZDFlMDc5MTM5Y2FkNTZhYjMxMTBmZjJjNDE4MDIyNjYyN2NhMDYzNzc4N2UwZDU2YzE0MGVmMDIwMWZlZmVmZGYwZTI1MzM4Yjg4NGI4NmU1YzhhYjEwMjNkYWZmMDA2ZWY4NjY5MTMyNDQ1ODZmMjJlNmYyMjI2NmQ2ZmFlMDlhNmY4MTNkNjUxZjhiMTQxODI5ZDRmZWZkYjE1NjIzYmUxM2FiYzQ1ZjdmYTYyODE4ZjllMDE4NmUyMDZkOGU2NjI3MDMwOWRhZTc2MTNiZmFlMDc0MzhjY2ViMjE1YzBjYzU2ZTMyOTA1NDkxMzA2MDk1Mzk0YzExYWM4ZDhlMDE5MGI2YmM2MTdlMmVhOTUxMmFhMWEyNGI1ZTA4MDE0OWIwYmU2MjRjMDhiNmIzYjQxYTU0YTg0Y2VkZmJkODYzM2NkN2E3NGMwMGE4NjFhYTE5OTkyMjQ5MmNjZDM3ODA2ZTQ2ZDcwMmQzODA3MzM5NjBhZjk5OTc0MjU0YzQwZTkzNjNlZTdiZWQ4MTE3MzdjYzY1NmMwNDU4OGQ0MWJkODc2ODgxOGM5ZTYxZjEwZDMwNzJhMzI4YTkwYzQ2NjA3MjgyYjE2MmMyY2JhODhmMjM2OWMyZDRiZTIxYTQ1OTkxZGQ0YjI4Y2ViOTFiZTY1ODA2MDRkODliYzExZGEzYmM2MjBkODM1YzA5MDQ5MzdlZDExMzM2MTAyZjE2ZmI2MzM3OThmMWM1NWEwMTEyNDljYTE3ZjE1ZmFhNzRiODNiNWZlYjYxOTljNzdjNDNhODU0NWNkMGE1NTU5YzA4OTk5ODNhMTM2NTFlYWMzY2YwYTUyYWY1MWNmODU5NzIyOWJiNzg2YTBkYzkwNjBjMTliOGQ2MDgyMDFmNzBkNWM1YjhhYWI1MWYyMTE1YTliM2NmZmUzMjA2YzAwNzAyMWM5MTczMDRjOThkYWQ3YzM5YzM3MDg5NTVhMWE5MmIwOWI0MjJjMTMxMzIyZTczNTg5YmMwZDA4ZDcxNmUwNzkzODBiZmM0MDgxNjJkMjRhOTQ1Y2IxZjM0OTJiMGJhYzFjNmJiNzA5NTE1MTBkMTc5M2UyMjEyNDE4ZThjZDJkZDRjYWQ5ODQ2ODA0NGY3MTgyMDU1Mzk3OWE4YTUxYTAzMDUwN2FhOTkyYjMzOTQxYjU4ZGZmMGNjZjk4OTljNmE1MTQ1NTE5M2E4Yzc3MTAzNDAyZGEwM2VkYjlmNmM1ODU0MjIyZGI2ZDYxZWRlNWZkYmNmMTcyYzBjOGI4MjA0ZjRhODI0N2RhMzZjNTE2NGEwMjY0ODlkNjI0YzZiN2QyNjM2ZjNjNzMxNTA0MTA3NjgzYjgzZmEwZjNjNGMwNjBiZDUyNjYyNDExZmVlZTBmN2M1YjM5M2YzMTExZjlmYWZiNjA2YTAwMzYwMmQyNzY4ZjdmM2Q3ZTk4YjNkNjFiNDAyNmRkYzEzZGI2YmVmODIwYTE3YjEyN2YyZDNkMGZkNzA1MDhhMDVlZGU1YjlmNTMxOGNmM2M2YTA2MTRkZWE0MzM3NTAwMTdmMDhkNjBjMTA2ZTYwNmY4NWY4OGU3MTQxMDM0NTQwYzY0ZDk0Mjk2Y2MyMjYxNmQ3ZDZmZTVlNTgyYjViM2FjZGEyM2QzNGY3ZmRjZTA3NGFhMTMyMjBkOWEwYzI5NTFiNjg1YTMzOGJlYTI3NDIzNmM2MDdmZGYxNGQ1MDQwMjI2MDIwZGMyOTE5OThjNTg4MjRlNTU4ODI3YTQ0NWNjNjE2NmUyZjg5YTg2YTMyODc1YTRhYTQ2NzQzMjVhMDk2ZThhNjQ5ZWYxOWFjN2QwMDhjMzRjN2E2YTgwMTI1NTlhZmYzNDZiMDI2ZTY2ZTM1YjYyMGUyODBiMjAyNTk4NDAzNjAzNGY2ZWUzNmRjNjNjY2YyZGUyMzNkNmE2YWFlNWVjNWQ4NDIzM2U0ODljYjAyMDUzMjY1NTg4OGVmNzkzMThkN2E1Y2QzODQ0NjJhNmJhMTI0N2YzOTYwYzY1ODU5YTRjZGM5MTAwZDhlYzMxMzRjMzljNzI1NGFhODAwODExMDRjOTMwNjA4MjQxODhiMTg4ZDg3OTQ1YjAwZTBmOTM5NWFhNmExMzJmMDQyZWIwMTQ5OTIyMjYyNDk4OWI0OTIwNzZjNjI1N2Y4YTk0ZDUzZTAyMTcwNTA3ZjEwYzgwNjAxODAwMWQyMDkyMDk2ZWUzYjVlNTRmODgyYTMwMDkxZjg0MTI1NDQ5YjA5OTA0ODBhNTgxNWQwNzcxYTYxNmM4YjhkZGExNTE4MDY2NzE5MjA5NTU1MTUwYjJiMDJkMGE2NjA0MzFkMzI4YmU4MmY2YzY1ZmYwMGZlOGRhMTgzNTM4OGY5M2M1NjIxYWMyY2M0MWQwY2NkYzEyNjA0ZGNkYjE5NTRmOGM3NzIxY2IxMmNlNDBiMmI1YjQyNGQ4MGNkMjA4YjQyZWUyZTM0ZTc4MmVlMzJjNGE5NjEwMzUzZTYxNTQwMmMwZTlkZWRlYjhjZGU0YjIzNDBmMzRhYTU2Y2Q5ZTIyMWMwNTExMzAxYWQ2NjI0MTlkMDZiYTgwMjBlMDAzODlhOTU2NWFhMzU0MDNjYzgwMTJjN2YwNDBlZDEzYjkwNGY3MDQ1NGU5MjAxOWRlNjBhYzAwNDg5NjhkMDJjMDIwODMyMjAxMjQ0ZmEwOGI3MDg5NGVhNDY2NTkxOThjNDIwOTIwNjUzYTAxNzZiYTliNzZmYTRkNTc1MWQ1YTAzMDA3ZTY4MmQ5OGZlMjYxZjJmZTEwMjI2MjNlYjcwMGZlMWQxNDMyMmEzNWExOThjMDk2NjkzOWQ3Mjk2MmNhNDAwYjcwNDk5Mjc0YzZmNzI2Zjg2ODU0NDlhYjllZTQxMzJjYmQ2YmFlNjljYTYwZTkzMWY1ZGIxZTgzODBlNDFjM2QyMzk4MmM5ZjMyNGQ4ODgzMzI2ZmYwMGJmN2IyMjZiY2Y3MjFlNTY1ODY2NGFjZWFhMDAwMzMyODJiMjFhNDU4ZTg3YWFlNDFiZTY4YmUzZDBkM2UwODJkOTk4YmMxOTk4Y2E0ODgwMmU0NmIxMWU0MzRjMzljNWYwYjRlYTUzZjBjYTAyYjYzMDc0MDU0ODY1Yjc5MTAwZTA2NDkwMDA2MjA5MWE5MDIwNGM1ZTA2ZGU5OGQzM2FiNTA1MmE0NDY4MmQxYTc3YzMwMzg5M2IwMWY1OWMyNDZiNDc2YzA5Zjg4Mjc2ZmRmYTYyOGQyM2M1OWYyMTg1YWFmMWQxNjljMjU5OWIxN2E3NGU3MDA1MzU5OGZlYzYyNTMwNzA1NGE2M2Y2MzFkMzZlZDAyM2I5M2U3ODA4ODA2YjMzOGVkM2E4YTY0NWZhNGM2ODQ2YzBmYmViYThiNmEzNTljNTA5YjkyY2EwNWUwMTA2NDkxZTc2YjVjOWI1ZmVmOGFiYzZhNjNkMDdhZTAwOTU2YTgyMzViZjk0Y2ZmMDBhYzIwMmFiMmI0MzQzMTI0OTA0N2YyZjljZGVkNjlkYjRjNTFmODk2MDJlMzZiMGRiNGQwZmE2Yzc3YmUzMzM4ZmUzYTNlNjY4NWRjYjEzMDA5ZGJjY2RmNGQzZWQ4MDczOGJlMzk5YjI3ODZjYWFhMTgxYTgxOTY3MzI0MTEwM2IxOTIyZmU1MTE4NDZhNTZhOGU0NjU2MDIyNGU0ZmZlMzAzMzM0YzQwMjVhNjAxYmU1M2I2MDY0OTAwNmEwNmFiNjI0NmRiZGE3ZDI2M2U5OGNmYTE5YThkNzZjYTFkZDJhMDJkOTQ4MTk1NWE0NTk1OGI2NjE2Y2M3MmMxMWZmMDBkYTAxZDNlNGY1MWFhN2YxNTkxZTk4MTM5NjViMzY2YjkzMzAwNzdkNmUwYzBiNjJmYzBmMjRhNjgxNzMyMmY3MmE3MmIwNWNjMDgyMDM5NTBkZGJlODNjZjA2ZTMwYTRhZDIyMTEyNWJhMDMwOTBjMDA5NzBiNmI0YTkzZDhkY2Y5YzZiZjJjZTFjODUwNmExNWNjMDE5Y2EyMGMxMjYwMGI5OGYzMWRmYjY5ODI5N2ExY2JjNTgwY2IwMjA0MWZjMjQwMGJmMzFkZTAwYmM3ZTc4ZDJlMGE4MDBjMDQzMTMwNGM5MTJiMTZiNTg1YTBlOTdiY2Y5NjBlODg3NGNhMDg1MzE3OTViNzcxNjhkN2ZkZTA5NTljMDBjOWI2OGMwODA0MzAyMzc5MTdiMThkM2Y1YzU0NGUyYTgwODI4ZTMzYWMxMTk0ZDgzMDMzMjE4MTkwZDM2ZGFkNzNlOTRlMTZiNTMwM2MzYTc0ZjI4YTZhYTIxNTYwMjg4ODUwMGM2NTIyMDFmNDhkMzE3MmIzMjA3Y2M0NmYxZGJkNjM2ZmNiYmUxMzRhMmQwMTViM2FiNWI0NjAwOWJkZjUwMjc1ZDYzN2MwMzk0ZGM5Mzk2ZjIyM2ZhOGRlNjNkNzZlZjgxYTIyMmU2MDAyYTE2MzJjMDQwOTY4MTc2ODE3ZGJlOWU1ODlmZjAwMjYyNDAxZDQyZTQ0Y2M0ODE2OWRiN2QzYmUwNTRkMDg5Y2YwNjNmYjVlZmI5YjlkYzZhMzAwNGE5YzUxOGQ5NmYxZGE2ZDNiZjk3OTZlNzFkYzIyNjk4MmMwZTU4NjIyMDk5YjgxZWZhZTlmN2M3MzAxOGY0Zjk4NGIwNTRhNjVhN2JkODI5MTM2M2I4MzFmODc0OGRmMGJmMDM0N2M1NTkzNDk1MGQ4NjUyYTQwMGMzMzAzMDIwMTJiMGZmOGI1ZDcyOGMyNDNlMjQ2NzE0ZDQyOTY2NjYwZDAwMDAxYzA5NmM4MzMxMDQzNDA1M2E1ZWZmMmVjOTFlN2UzM2Q1MDNhNGIzMDJiOTMyNDg1NTI0ODJjNjNhY2I0MDUyOGM3ZjExMWFjNjI2YWUzNjJiZmMzZTVkYTMzMjMxODljYWNhMDBiMDBhMTgwMDQ0NDc0Y2M3NjlkNzE0YTVmMGY1NDVjY2UxOWExNDE2MDA0ZjUxNjUzNjAwYjBkM2E3ZTZiNzk2Zjg1ZWFmYzUwNGEzMTZjYjU1ODliNTMyZDk0NDQxMjAyYjI4Mzk4MTEyMGM5NjA3MjlkOGUzMjRmMWY1MGIxNzM1MmEyYTE4ZjBkMTNlNTExMDAwMWYyYzY1MGEwNjUyNDFmOWE2NzEzNjE4ZDJlMzM4YmUxYTllN2E2NmVkMWUxZTZjYjczMzE5ODMzMTlkNjAwODgyN2E0ZTE1ZTJmOWYwNjMzNzQwNGE4MDQwNzY4MGJkNDNmODYxNzI4MjQ4NjMyNzZiMGMyMWNhY2U3OTAyYTQ5NzYyZTI5YTY1MWZjNGNjMzU1NDM3Mzk3YTgwMDAxOTY4YmMxYzc1Mzg2NTI0YWI1NDgyNTQ2NzBhYTBhOTY2MDBjOTE5NGU0YmM4YjgwMDVmYjQ2MjZhODk0Nzk4NTQ2MzRkZTY2NmRlMTBmZTFhODQxOTgxMzk0MjEzMzk4Y2RjNDRiNmQ2YzBlYjQ4Y2E1MDgxMDgzYzQ1MWQzMDRiMzY3MDAxMTJjN2E5MDdjYzM1MzEyMjA4MzU1ZTE4Y2JhMWFiNGYyZTY5MzUzMmY2MDBmNTljYzI1YTI3NjgxYTVhNjMxYzRhMmZhMmFkNTJjYjc1MDMzMTQ2MDRlNTE5NGMxMTA2NDEyY2E1ODk5MTIzMDFjZTA4YzkzMjVlYzY3ZTU2MTJkNjAxMmRkMzYyYTViMzQ0OTkyMGVhMzA3YTI2ZTQ1MzA4YTBjODc2NTI1NDQxMjRjOTEyMTBjNWU2YzM1MjYwODAzMWEzYzBmMjdhOTUzYTA2NDBjMmU1MGQzZjBkOWQxOTk2NThiMTk2MzAwODk5ODgzNjg5MDIzNGI5NjdjMjBjNTk4NTdhODU3MjkwMTE5MjIxZTU1NDk2MGE2NjIxYjMwMTc5MTAwZjc5OTIxYWM1NGEyMTkxNTZhMTY4MWZjZDJjYmExOTBiMTlhZTA0YmRkYjczYjY5NmUwNzkwZDVhOGVjYzk0NWQ4MTEwMGQ0MzkxNDBkMjQwM2IwMWE0NzU0NDZiNzlmNzNjYjdlMWRhMTQ5Y2Q0MTlkOWFmZDRlZTU4NWUzZjA5ZTk5MTAyZmFkY2Y3MzNhY2NjMjIwZTM1MzhhNmJjODcyNWY4NWVhMTdmMTJiYjI4MjQ3NTUzODU2YjVjNDE2MmJhZjllYmE1ZWQ3ZjU3YzM1MWE3NDQ0NTJhNDhiYTAyMTE0MmRhMjI0Zjc4ZmFlMmNkNTYyMDhmZGZiZTAzNTc4YTgxNmM1NDMzNDVlYTc4NjllMjA1MGY5NDE2MGE3YTQzNDc1MDUzMDBjNGM4MWJjNjA2ZGM0MDFiZmFkZmYwMDVjMjM1Mzg4Mjc0ZmJmN2MwYWM2MmU0ZWYyMGRiZjU4MThhMTg3YWJkNTk4NGM5MTFhZWMwOTNmYTljNTBkNDI3ZmM2MDY4MGNjMDA0YzZhNzE3MDQxMjAwYmRmNTIyZDY4MzM3MTdmOTg1Yzc5ZjYzODA4NWU1ODI5MzBjNjQ4MWU0MjI3NmYzMWY1YzFkNjg0Nzk3YTdlY2UyODViNWQ0MWYyZGZmYmVkOGVlNjU5NTI2ZTZlMDc5OWQzZDI2ZDgwMjJhMDg4ZmFlOTdiNjBiNGM0MGQwNDAxZmJiNjE1N2E5ZThiM2IxODkyM2ZkMGQyZGFmYjYwNTUzOGEwNjA0NGZiMWY3ZmZkYTNjY2UwMWQ3YWIyMGVhMmRkOGZlN2Y1YzAxZjg4OWI2OWI0ODNmNDIzZDMwOGYxN2M0YWFmZTI4ZDYwNTg5ZDdkZjczMjcwODU1ZTM0MTIxNDAwNWJlNmRhMjNiZjkwOWRmYmUyMGQwZTE5ODUyNDA4MmEzYjAwMjMzNTQ2MmNjNDg5ZDU4ZGNkYWRhZjZjMmJjNGYxNTUyNDkwNTRhNDBkMzU5MjZmNzg4ODBhMDQ1YWZlNWFlMTBhOTk4N2NlMDkwNjU0MTk1MTlhNmQ2MDRkYzkyNDQ0Y2M5ZjVjMGQ5NmJiNTUyMTY5ODVhNjE0YmFiOTNkNjJhMDkxNjA1NGE4OTAwMjlmMjI3YmQ5YTBiNWI4ODc3ZTk0NjIwZTUyNjQ0OGIwOTE2Mzk2MjczMTViMThlZmVhM2ExZDBiMGM4NmEzMjg1MDQ4MDE5OTk2YzI0ZmNiZWUwNDliMWQ2NDYxZGEyNDM0MDc1NjMyMzM0ZGEwMTUzMjU0OTBjMTU0MWNlMjM3NjFlNDMwYjcwZjQxNDU0MjQyNDk2MTkwYmU1YmJhYWM4NTY2Yjk4NTkxMDM1ZjliNDE4MmFlMzk3MzU0NTE1MDBiODZjYzAzMTYzMTNhNGFhOTAzNDM2OGRiYjYxYmUwMzkyZDU1NmE2YzA4MjgxNzI5YTcwYzQ2YmFlNTkxMTA2ZjMzYTVhNzRjMzVjMWYyYzY1MzlkOWMxNTMyYzIyYzU2MzJlOGMyMGU1NWViMjI3MzE5NmQ3NDE4NmI4MmU2MzRkZGVhZWEzMjNlNDJjYzNlNmI0OWM5ZmQyM2Y0YzAyZGMwZjA4ZTg0NzRlNjY2MjczNDBjYjk0NmQ2M2E4ZDg2ZGQ1M2IwODdhODE0ODE1MmM3YWE3NDIwZGE1NGMwZDg4ZWE5MWFlYzcwOWQ3ZTM1YWJkMTc0YTJkNzJjZjRjZDU0MDA2NDIwOTkyMDMxMzMxMDU2ZGY4YmM4MThhNTBlNjBjNjk0MDJlNWQ3YTQ5NWE2NzMxNmIwY2NjMDJlNTU5OTBkMWExMTM2ODA2MDg2YjhkYWM2YzI0NjY2Njg1MWUxOTcwMjE0OTE5OGE5Mzk1NjU2NjZkYTgxMzI2NzAyYThlZmEzMmIxYjExYjhjZDAwMTEzMmMyM2YxMmRhNmYxZDQzNGMyOWMyNTMwMDg0NTQ4ZjA5OGM0Yzg1OTI2NzRkMTRiMmJjY2MxODlkYjRjMWM1NzUwNTU3MmI0OWI5NjAyNmMwODFhNGNjYjEyYzY0MGZhNjAwOWMyYTA0MTBhNmMzN2YxMzM2ZDEyNThmNTM0ZTUxMDI0OWMwOWI4OTgyNTg5NWJiMDUwNDlkNDkzZGYzMDk5MjYzZjJjMTVlYTA3MjAwMWY2MjQ2ZGI0ZjYyIg" /><br>
					Date Reported: {{marker.dateCreated}}<br>
					Pothole ID: {{marker.potholeId}}<br>
					Current Status: {{marker.status}}<br>
					Severity:<b> Not Inspected</b><br>
					<button v-on:click="deletePothole(marker.potholeId)" id="deleteBtn" 
					v-show="checkUser()">Delete</button>
					<button v-on:click="schedule(marker.potholeId)" v-show="checkUser()">Schedule</button>
					<router-link v-bind:to="{ name: 'claim', params: { id: marker.potholeId }}">
						<button v-show="checkLoggedIn()">Submit Claim</button>
                    </router-link>
					<router-link v-bind:to="{ name: 'report', params: { id: marker.potholeId }}">
						<button v-show="checkLoggedIn()">Submit Picture</button>
                     </router-link>
				</map-info-window>

				<map-info-window v-if="marker.severity == 5" class='five' :lat="marker.latitude + .00001" :lng="marker.longitude">
					<img src="../../assets/pothole_sample.jpg" width="100%" height="160"/><br/>
					Date Reported: {{marker.dateCreated}}<br>
					Pothole ID: {{marker.potholeId}}<br>
					Current Status: {{marker.status}}<br>
					Severity: <b>{{marker.severity}}</b><br>
					<button v-on:click="deletePothole(marker.potholeId)" id="deleteBtn" 
					v-show="checkUser()">Delete</button>
					<button v-on:click="schedule(marker.potholeId)" v-show="checkUser()">Schedule</button>
					<router-link v-bind:to="{ name: 'claim', params: { id: marker.potholeId }}">
						<button v-show="checkLoggedIn()">Submit Claim</button>
                    </router-link>
					<router-link v-bind:to="{ name: 'report', params: { id: marker.potholeId }}">
						<button v-show="checkLoggedIn()">Submit Picture</button>
                     </router-link>
				</map-info-window>

				<map-info-window v-if="marker.severity == 4" class='four' :lat="marker.latitude + .00001" :lng="marker.longitude">
					<img src="../../assets/pothole_sample.jpg" width="100%" height="160"/><br/>
					Date Reported: {{marker.dateCreated}}<br>
					Pothole ID: {{marker.potholeId}}<br>
					Current Status: {{marker.status}}<br>
					Severity: <b>{{marker.severity}}</b><br>
					<button v-on:click="deletePothole(marker.potholeId)" id="deleteBtn" 
					v-show="checkUser()">Delete</button>
					<button v-on:click="schedule(marker.potholeId)" v-show="checkUser()">Schedule</button>
					<router-link v-bind:to="{ name: 'claim', params: { id: marker.potholeId }}">
						<button v-show="checkLoggedIn()">Submit Claim</button>
                    </router-link>
					<router-link v-bind:to="{ name: 'report', params: { id: marker.potholeId }}">
						<button v-show="checkLoggedIn()">Submit Picture</button>
                     </router-link>
				</map-info-window>

				<map-info-window v-if="marker.severity == 3" class='three' :lat="marker.latitude + .00001" :lng="marker.longitude">
					<img src="../../assets/pothole_sample.jpg" width="100%" height="160"/><br/>
					Date Reported: {{marker.dateCreated}}<br>
					Pothole ID: {{marker.potholeId}}<br>
					Current Status: {{marker.status}}<br>
					Severity: <b>{{marker.severity}}</b><br>
					<button v-on:click="deletePothole(marker.potholeId)" id="deleteBtn" 
					v-show="checkUser()">Delete</button>
					<button v-on:click="schedule(marker.potholeId)" v-show="checkUser()">Schedule</button>
					<router-link v-bind:to="{ name: 'claim', params: { id: marker.potholeId }}">
						<button v-show="checkLoggedIn()">Submit Claim</button>
                    </router-link>
					<router-link v-bind:to="{ name: 'report', params: { id: marker.potholeId }}">
						<button v-show="checkLoggedIn()">Submit Picture</button>
                     </router-link>
				</map-info-window>

				<map-info-window v-if="marker.severity == 2" class='two' :lat="marker.latitude + .00001" :lng="marker.longitude">
					<img src="../../assets/pothole_sample.jpg" width="100%" height="160"/><br/>
					Date Reported: {{marker.dateCreated}}<br>
					Pothole ID: {{marker.potholeId}}<br>
					Current Status: {{marker.status}}<br>
					Severity: <b>{{marker.severity}}</b><br>
					<button v-on:click="deletePothole(marker.potholeId)" id="deleteBtn" 
					v-show="checkUser()">Delete</button>
					<button v-on:click="schedule(marker.potholeId)" v-show="checkUser()">Schedule</button>
					<router-link v-bind:to="{ name: 'claim', params: { id: marker.potholeId }}">
						<button v-show="checkLoggedIn()">Submit Claim</button>
                    </router-link>
					<router-link v-bind:to="{ name: 'report', params: { id: marker.potholeId }}">
						<button v-show="checkLoggedIn()">Submit Picture</button>
                     </router-link>
				</map-info-window>

				<map-info-window v-if="marker.severity == 1" class='one' :lat="marker.latitude + .00001" :lng="marker.longitude">
					<img src="../../assets/pothole_sample.jpg" width="100%" height="160"/><br/>
					Date Reported: {{marker.dateCreated}}<br>
					Pothole ID: {{marker.potholeId}}<br>
					Current Status: {{marker.status}}<br>
					Severity: <b>{{marker.severity}}</b><br>
					<button v-on:click="deletePothole(marker.potholeId)" id="deleteBtn" 
					v-show="checkUser()">Delete</button>
					<button v-on:click="schedule(marker.potholeId)" v-show="checkUser()">Schedule</button>
					<router-link v-bind:to="{ name: 'claim', params: { id: marker.potholeId }}">
						<button v-show="checkLoggedIn()">Submit Claim</button>
                    </router-link>
					<router-link v-bind:to="{ name: 'report', params: { id: marker.potholeId }}">
						<button v-show="checkLoggedIn()">Submit Picture</button>
                     </router-link>
				</map-info-window>
			</div>
			<!-- <map-info-window :lat="-23.344" :lng="129.036">
				<span style="background:red;">Test 2</span>
			</map-info-window>
			<map-info-window :lat="-22.344" :lng="128.036">
				<img
					src="https://1.bp.blogspot.com/_S2Wkv5XI8JI/TUL5MbRnT-I/AAAAAAAAACA/g8V_yjojceA/s1600/woot.jpg"
					height="100px"
				/>
			</map-info-window> -->
		</div>
</div>
</template>

<script>
	import MapMarker from "../components/MapMarker"
	import MapInfoWindow from "../components/MapInfoWindow"
	import PotholeService from "../services/PotholeService" 
	import $ from 'jquery'

	window.createReport = function() {
		// console.log(report);
			
	
	}

	export default {
		components: {
			MapMarker,
			MapInfoWindow
		},
		data: () => ({
			map: null,
			markers: [],
			newReportMarker: {
				latitude: null,
				longitude: null,
				//img
			},
			claim:  {
				name: null
			},
			currentPosition: {
				lat: null,
				lng: null
			},
			lat: null,
			lng: null,
			potholeStatus: {
				status: 'unscheduled',
			}
		}),
		created: function() {
			PotholeService.list().then( (response) => {
				this.$store.commit("ADD_POTHOLES", response.data);
			}).catch(error => {
				if(error.response.status == 400) {
					console.log(error.response.status);
				}
			});
			
			PotholeService.getImages().then( response => {
				this.$store.commit("LIST_IMAGES", response.data);
			}).catch(error => {
				if(error.response.status == 400) {
					console.log(error.response.status);
				}
			});

			
		},
		methods: {
			getMap(callback) {
				let vm = this
				function checkForMap() {
					if (vm.map) callback(vm.map)
					else setTimeout(checkForMap, 200)
				}
				checkForMap()
			},
			submitClaim() {
			
			},
			checkUser() {
				if(Object.keys(this.$store.state.user).length == 0 || Object.values(this.$store.state.user.authorities[0]).includes('ROLE_USER')) {
					return false;
				}
				return true;
			},
			checkLoggedIn() {
					if(Object.keys(this.$store.state.user).length == 0) {
						return false;
					}
					return true;
			},
			deletePothole(id) {
				PotholeService.deletePothole(id).then(response => {
					this.$store.commit("DELETE_POTHOLE", response);
					location.reload();
				}).catch(error => {
					if(error.response.status == 400) {
						console.log(error.response.status);
					}
				});
			},
			placeMarker(location) {
				new window.google.maps.Marker({
					position: location, 
					map: this.map
				});
			},
			createReport() {
				PotholeService.createReport(this.newReportMarker).then(() => {
				location.reload();
			}).catch(error => {
				if(error.response) {
					console.log('Error submitting new report.');
				} else if (error.request) {
					console.log("Error submitting new board. Server could not be reached.");
				} else {
					console.log("Error submitting new board. Request could not be created.");
				}
			})
			},
			getLocation() {
				alert('works');
				navigator.geolocation.getCurrentPosition(position => {
					console.log('test');
					this.currentPosition = {
						lat: position.coords.latitude,
						lng: position.coords.longitude
					};
				});
			},
			handleLocationError(browserHasGeolocation, infoWindow, pos) {
				infoWindow.setPosition(pos);
				infoWindow.setContent(
					browserHasGeolocation
					? "Error: The Geolocation service failed."
					: "Error: Your browser doesn't support geolocation."
				);
				infoWindow.open(this.map);
			},
			showPosition(position) {
                this.currentPosition = {
                    lat: position.coords.latitude,
                    lng: position.coords.longitude
                }
                this.setPosition();
			},
			setPosition() {
				this.map = new window.google.maps.Map(this.$refs["map"], {
                    center: { lat: this.currentPosition.lat, lng: this.currentPosition.lng },
                    zoom: 15
                });
			},
			simulateClick(el, etype){
				if (el.fireEvent) {
					el.fireEvent('on' + etype);
				}else {
					var evObj = document.createEvent('Events');
					evObj.initEvent(etype, true, false);
					el.dispatchEvent(evObj);
				}
			},
			openClaimForm(pothole) {
				
				
				console.log(pothole.potholeId);
				let cover = document.createElement('div');
				cover.style.height = '100vh';
				cover.style.width = '100vw';
				cover.style.backgroundColor = 'rgba(0,0,0,.5)';
				cover.style.position = 'relative';
				cover.style.zIndex = '9';

				document.getElementById('map').appendChild(cover);



				let form = document.createElement('div');
				form.style.width = '70%';
				form.style.height = '55%';
				form.style.backgroundColor = '#EFEFEF';
				form.style.position = 'fixed';
				form.style.left = '0';
				form.style.right = '0';
				form.style.top = '0';
				form.style.bottom = '0';
				form.style.margin = 'auto';
				form.style.zIndex = '10';
				form.style.padding = '10px';
				form.innerHTML =
				"<h2>Pothole: " + pothole.potholeId + "</h2>" +
				"<p>Describe the damage:</p><textarea style='width: 99.5%;  min-height: 25%;'></textarea><br><br>" + 
				"<label for='name'>Name: </label><input type='text' class='claimName' style='width: 94%;' v-model='claim.name'/><br><br>" +
				"<label for='number'>Phone Number: </label><input type='text' style='width:  88%;' /><br><br>" +
				"<label for='email'>Email: </label><input type='email' style='width:  94%;' /><br><br>" + 
				"<label for='date'>Date Occurred: </label> <input type='date' /><br><br>" + 
				"<label for='year'>Vehicle Year: </label><input type='text' style='width: 89.5%;' /><br><br>"  + 
				"<label for='model'>Vehicle Model: </label><input type='text' style='width: 88%;' /><br><br>" + 
				"<label for='year'>Vehicle Make: </label><input type='text' style='width: 88.5%;' /><br><br>" + 
				"<center><button type='submit' id='claimBtn'  style='font-size: 24px;' onclick='submitClaim()'>Submit Claim</button></center>";



				document.getElementById('map').appendChild(form);
			},
			schedule(id) {
				let schedule = {potholeId: id};
				PotholeService.schedule(schedule).then(response => {
				location.reload();
					console.log(response);
				})   
			},

			checkSeverity(status){
				if (status === 5){
					return 'red';
				} else if (status == 1){
					return 'blue';
				}
			}
			
			
		},

		mounted() {
			
			this.map = new window.google.maps.Map(this.$refs["map"], {
                center: { lat: 39.952465, lng: -75.164062 },
                zoom: 15
			})


			this.$nextTick( () => {
				if (navigator.geolocation) {
					navigator.geolocation.getCurrentPosition(
						(position) => {
							const pos = {
								lat: position.coords.latitude,
								lng: position.coords.longitude,
							};
							infoWindow.setPosition(pos);
							infoWindow.setContent("Location found.");
							infoWindow.open(this.map);
							this.map.setCenter(pos);
							},
							() => {
								this.handleLocationError(true, infoWindow, this.map.getCenter());
							}
						);
				} else {
					this.handleLocationError(false, infoWindow, this.map.getCenter());
				}
			});

		
				
			let infoWindow = new window.google.maps.InfoWindow();
			const locationButton = document.createElement("button");
			locationButton.id = "panButton";
			locationButton.textContent = "Pan to Current Location";
			locationButton.classList.add("custom-map-control-button");
			this.map.controls[window.google.maps.ControlPosition.TOP_CENTER].push(locationButton);
			locationButton.style.fontSize = "20px";
			locationButton.style.marginTop = "14vh";
			locationButton.style.padding = "5px";
			locationButton.style.boxShadow = "0 2px 6px rgba(0, 0, 0, .3)";
			locationButton.style.textAlign = "center";
			locationButton.style.fontWeight = "normal";
			


			locationButton.addEventListener("click", () => {
				if (navigator.geolocation) {
					navigator.geolocation.getCurrentPosition(
						(position) => {
							const pos = {
								lat: position.coords.latitude,
								lng: position.coords.longitude,
							};
							infoWindow.setPosition(pos);
							infoWindow.setContent("Location found.");
							infoWindow.open(this.map);
							this.map.setCenter(pos);
							},
							() => {
								this.handleLocationError(true, infoWindow, this.map.getCenter());
							}
						);
				} else {
					this.handleLocationError(false, infoWindow, this.map.getCenter());
				}
			});

			
			

				

			// if(navigator.geolocation) {
            //     navigator.geolocation.getCurrentPosition(this.showPosition);
			// } 
			// else {
            // //     this.map = new window.google.maps.Map(this.$refs["map"], {
            // //         center: { lat: 39.952465, lng: -75.164062 },
            // //         zoom: 15
            // //     });
            // //     console.log('not supported');
            // // }
			
			
			// let infoWindow = null;
			if(!Object.keys(this.$store.state.user).length == 0) {
				infoWindow = new window.google.maps.InfoWindow({
					content: "<span style='font-size: 24px;'>Click the map to report a pothole!</span>",
					position: this.map.center,
				});
				console.log(this.map.center);
			} else {
				infoWindow = new window.google.maps.InfoWindow({
					content: "<span style='font-size: 24px;'>Click the map to report a pothole! You must be logged in.</span>",
					position: this.map.center,
				});
				
			}
			infoWindow.open(this.map);
			this.map.addListener('click', (event) => {
				alert('click');
				infoWindow.close();
				let loc = JSON.stringify(event.latLng.toJSON());
				//changes JSON to array to access and save in dummy var
				let locArray = JSON.parse(loc);
				this.newReportMarker = {};
				this.newReportMarker.latitude = locArray.lat;
				this.newReportMarker.longitude = locArray.lng;
				this.$store.commit('ADD_REPORT', this.newReportMarker);
				// alert(event.latLng);
				if(this.$store.state.user.authorities.some(name => name.name === 'ROLE_EMPLOYEE' || name.name === 'ROLE_USER' || name.name === 'ROLE_ADMIN')) {
					let contentString = "<span style='font-size: 24px;'>Would you like to submit this pothole report?</span><br>" + 
					"<br><center><input type='button' id='submit-report' value='Submit' onclick='createReport()'> </center>";
					infoWindow = new window.google.maps.InfoWindow({
						content: contentString,
						position: event.latLng,
						ZIndex: 99
					});
				}
				
				infoWindow.open(this.map);
				window.google.maps.event.addListener(infoWindow, 'domready', function() {
					let submitReportBtn = document.getElementById('submit-report');
	
					submitReportBtn.addEventListener('click', () => {
						PotholeService.createReport().then(() => {
							// let authorized = this.$store.state.userIsAuthorized;
							location.reload();
							this.$store.commit("SET_AUTH", true);
						}).catch(error => {
							if(error.response) {
								console.log('Error submitting new report.');
							} else if (error.request) {
								console.log("Error submitting new board. Server could not be reached.");
							} else {
								console.log("Error submitting new board. Request could not be created.");
							}
						})
					})
				})
			});
			$('panButton').click();
			document.querySelectorAll('button').forEach(item => {
				console.log(item);
			})
			document.querySelector('.custom-map-control-button').dispatchEvent(new Event('click'));
					// this.simulateClick(document.getElementById("panButton"), "click");
					// document.getElementById('panButton').click();
		}
	}
</script>

<style>
/* Always set the map height explicitly to define the size of the div
       * element that contains the map. */
#map {
		height: 100vh;
		background: gray; 
		position: relative;
		z-index: 1;
	}

#custom-map-control-button {
  display: flex;
  justify-content: center;
  align-items: center;
  cursor: pointer;
  border-radius: 5em;
  color: #fff;
  background: linear-gradient(to right, #9C27B0, #E040FB);
  border: 0;
  font-family: 'Ubuntu', sans-serif;
  font-size: 15px;
  box-shadow: 0 0 20px 1px rgba(0, 0, 0, 0.04);
  box-sizing: border-box;
  /* padding-top: 1; */
  width: 100%;
  height: 7%;
}

/* .gm-style .gm-style-iw {
	background-color: #E040FB
} */

.pothole_status.inspected {background-color: aqua;}


.zero{
	border: white
}
.five{
	border: 10px solid #4B0082;
}

.four{
	border: 10px solid #8A2BE2	;
}

.three{
	border: 10px solid #FF00FF	;
}

.two{
	border: 10px solid #EE82EE	
}

.one{
	border: 10px solid #D8BFD8	;
}

</style>