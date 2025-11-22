@echo off
echo ============================================
echo   Deleting Latest Patient Records...
echo ============================================

curl -X DELETE "http://localhost:8089/api/delete/131c156e-91c3-4680-b75f-b2705ab091ac"
curl -X DELETE "http://localhost:8089/api/delete/5a75c86b-518c-4904-ae1d-d57b9a6f90a0"
curl -X DELETE "http://localhost:8089/api/delete/1cb25920-4e52-407c-8df1-168d28a6a9dc"

echo ============================================
echo   All selected patients deleted successfully!
echo ============================================
pause
