
n = 20
output = "layout_pauta.txt"
archivo = open(output, 'w')

for i in range(1,n+1):
    number = str(i)
    archivo.write('        <TableRow\n')
    archivo.write('            android:layout_width="match_parent"\n')
    archivo.write('            android:layout_height="match_parent"\n')
    archivo.write('            android:orientation="horizontal">\n')
    archivo.write('            <TextView\n')
    archivo.write('                android:layout_width="wrap_content"\n')
    archivo.write('                android:layout_height="wrap_content"\n')
    archivo.write('                android:textAppearance="?android:attr/textAppearanceSmall"\n')
    archivo.write('                android:layout_gravity="center"\n')
    archivo.write('                android:text="'+number+'"\n')
    archivo.write('                android:id="@+id/textViewRow'+str(i)+'"\n')
    archivo.write('                android:layout_column="1" />\n')

    for j in range(2,6):
        archivo.write('            <RadioButton\n')
        archivo.write('                android:layout_width="wrap_content"\n')
        archivo.write('                android:layout_height="wrap_content"\n')
        archivo.write('                android:id="@+id/radioButtonRow'+str(i)+'_'+str(j)+'"\n')
        archivo.write('                android:layout_column="'+str(j)+'" />\n')
        archivo.write('                android:checked="false" />\n')

    archivo.write('            <Button\n')
    archivo.write('                android:layout_width="40dp"\n')
    archivo.write('                android:layout_height="40dp"\n')
    archivo.write('                android:text="-"\n')
    archivo.write('                android:id="@+id/buttonRefreshPreguntaRow'+str(i)+'"\n')
    archivo.write('                android:layout_column="6" />\n')
    archivo.write('        </TableRow>\n')
        
archivo.close()







