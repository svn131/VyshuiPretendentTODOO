import java.util.*;

public class Main {
    public static void main(String[] args) {

        List<Integer[]> combinations = new ArrayList<>();   //@TODO здесь будут добавлены масивы чисел(уникальные комбинаций из 5 разных чесел) в количестве 100 шт рамдомно выбранные из 100 000 возможных комбинаций

        int successfulAttempts = 0;

        int summaVsehPoputokZaVsyuIgru = 0;

        int igra = 0;

        while (igra < 1000) {  //@TODO  количество игр для 1 теста
            igra++;
            boolean ugadalis9 = true; //@TODO  была ли угадана комбинация с 9ти попыток измениться нна folse только когда будут истрачены все 9 попыток в одной из игр
            ArrayList<int[]> listMasivovSovpadenuy = new ArrayList<>();

            Random rand = new Random();

            while (combinations.size() < 100) {
                Integer[] combo = new Integer[5];
                rand = new Random();
                combo[0] = rand.nextInt(10) + 0;
                combo[1] = rand.nextInt(10) + 10;
                combo[2] = rand.nextInt(10) + 20;
                combo[3] = rand.nextInt(10) + 30;
                combo[4] = rand.nextInt(10) + 40;
                if (!combinations.contains(combo)) { //@TODO  Проверка на уникальность вкладываемой комбинации - что такой еще не было - переделать на сет
                    combinations.add(combo);
                }
            }

            int[] counts = new int[50]; //@TODO вспомогательный масив наполняет индеск - который соотвествует  числу из комбинации значение - сколько раз данное число встретилось в списке из 100 комбинаций цифр
            for (Integer[] combo : combinations) {
                for (int num : combo) {
                    counts[num]++;
                }
            }

            List<Count> listChisel = new ArrayList<>();

            for (int i = 0; i < 50; i++) {
                listChisel.add(new Count(i, counts[i])); //@TODO заполняем лист значениями типа Caunt  эта сама цифра и ее количесвто в списке
            }

            Collections.sort(listChisel, new Comparator<Count>() {
                public int compare(Count c1, Count c2) {
                    return c2.getCount() - c1.getCount(); //@TODO сортируем все цифры в листе от 0 до 50 по принципу чем больще совпадений в списке тем цифра выше
                }
            });

            for (Count count : listChisel) {
                System.out.println("Цифра " + count.getNum() + " встретилась " + count.getCount() + " раз"); //@TODO отладочная информация в консоль
            }

            final int[] comparisonCount = {0};

            Collections.sort(combinations, (combo1, combo2) -> { //@TODO  вот теперь сортируем  сам список из 100 комбинаци по принципу чем больще в комбинации весомых чисел у которых большое количество совпадений на список , тем выше комбинация
                int count3 = 0;
                int count1 = 0, count2 = 0;
                for (int i = 0; i < 5; i++) {
                    count1 += counts[combo1[i]];
                    count2 += counts[combo2[i]];
                }

                System.out.println("Comparing: " + Arrays.toString(combo1) + " and сравнение " + Arrays.toString(combo2));


                comparisonCount[0]++; //@TODO  Выяснить почему каждый раз разное количество сравнений
                return count2 - count1;

            });

            System.out.println("Общее количество сравнений в компараторе: " + comparisonCount[0]);

            for (Integer[] combo : combinations) { //@TODO вывод отсортированого списка в консоль
                for (int num : combo) {
                    System.out.print(num + " ");
                }
                System.out.println();
            }

            System.out.println("А вот и загаданное число программой");

            Integer[] zagadanayaCombinaciya = combinations.get(rand.nextInt(combinations.size())); //@TODO  Выбираеться одна из 100 комбинаций рандомно
            for (int num : zagadanayaCombinaciya) {
                System.out.print(num + " ");
            }
            System.out.println();

            System.out.println("-------------------------------------------------------------------------");

            Integer[] vishuiPredentent = combinations.get(0); //@TODO  берем самыую высшую комбинацию - тоесть ту у которой болеще всего весомых чисел - тут больше даже интересует промах так как на основании ответа например 0 совпадений мы уберем очень много комбинаций имеющих эти цифры. Даже в случае трех совпадений мы сможем убрать комбинации имеющие три из этих цифр в себе а так как это цифры распространенные такие с большой долей вероятности будут.
            System.out.println("Высший прединтент: " + Arrays.toString(vishuiPredentent));

            int poputka = 0; //@TODO  номер попытки в данной игре - одной например из 1000 по умолчанию
            int count = 0; //@TODO  число совпавших цифр в комбинации

            while (count < 5 && poputka < 10) {
                count = 0;
                List<Integer> vishuiPredNums = Arrays.asList(vishuiPredentent);

                for (int i = 0; i < 5; i++) {
                    if (vishuiPredNums.contains(zagadanayaCombinaciya[i])) {  //@TODO  проверяем количество совпадений с загаданым числом
                        count++;
                    }
                }

                System.out.println("Количество совпадений: " + count);
                poputka++;


                if (count == 5) {
                    ugadalis9 = false;
                    System.out.println("Вы выиграли! Количество попыток: " + poputka);
                    System.out.println(Arrays.toString(zagadanayaCombinaciya));


                    summaVsehPoputokZaVsyuIgru = summaVsehPoputokZaVsyuIgru + poputka;

                    if (igra == 1000) {
                        System.out.println("Сумма всех попыток " + summaVsehPoputokZaVsyuIgru);
                    }


                    if (poputka <= 10) {
                        successfulAttempts++; //@TODO  общий подсчет выйграных игр из 1000 по умолчанию - тоесть где угадали до 10 попыток
                    }
                    break;
                } else if (count > 0 && count < 5) { //@TODO  count = 1 , 2, 3, 4
                    if (count == 1) {
                        int[] masivNeskolkihSovpadenuy = new int[6]; //@TODO делаем специальный масив в котором есть информация о количестве совпадений для дальнейщегоисключения других комбинаций на основе этой информации  -например количесьво совпадений один - значит все комбинации в которых присутсвую две и более цифры из этого списка мы можем удалять так как они проигрышные.
                        //сбор данных
                        masivNeskolkihSovpadenuy[5] = 1; //@TODO 5 индекс хранит в себе число совпадений с загаданой комбинацие
                        masivNeskolkihSovpadenuy[0] = vishuiPredentent[0];
                        masivNeskolkihSovpadenuy[1] = vishuiPredentent[1];
                        masivNeskolkihSovpadenuy[2] = vishuiPredentent[2];
                        masivNeskolkihSovpadenuy[3] = vishuiPredentent[3];
                        masivNeskolkihSovpadenuy[4] = vishuiPredentent[4];

                        listMasivovSovpadenuy.add(masivNeskolkihSovpadenuy); //@TODO список  специальных масивов
                        System.out.println("Сохранено для анализа на 10й попытке ****************************" + Arrays.toString(masivNeskolkihSovpadenuy)); //@TODO отладка
                        //сбор данных

                        System.out.println("Удаляем все комбинации содержащие 2 и более совпадений с данной комбинацией");
                        List<Integer[]> combosToRemove = new ArrayList<>(); //@TODO создаем новый список масивов комбинаций в котором будут отсутсвовать удаленые комбинации
                        for (Integer[] combo : combinations) {
                            if (!Arrays.equals(combo, vishuiPredentent)) { //@TODO сравниваю что это не высший предендент - потому что высший придендент это и естьта комбинация с которой сейчас работаем
                                List<Integer> comboNums = Arrays.asList(combo);  //@TODO остальные комбинации по очереди мы помещаем во временный лист интеджеров comboNums
                                int sameNumCount = 0; //@TODO переменная для подсчета количесва совпадений  от высщего придендента к комбинации на которой в данный момент итерация цикла
                                for (int num : vishuiPredentent) {
                                    if (comboNums.contains(num)) { //@TODO  проверяет сколько раз в comboNums будет совпадений из вышего притендента
                                        sameNumCount++;
                                    }
                                }
                                if (sameNumCount >= 2) {  //@TODO  в случае двух и более совбадений  эта комбинация помещаеться в лист - список комбинаций которые необходимо удалить и общего списка
                                    combosToRemove.add(combo);
                                }
                            }
                        }
                        combinations.removeAll(combosToRemove); //@TODO  удалем комбинаций с двумя и более совпадениями при условии что у высщего  предендента совпадение было всего одно см. выше count == 1
                    }

                    if (count == 2) {  //@TODO  два совпадения говорят о том что 3 цифры  тут 100% являються проигрышными - значит любую комбинацию которая содержит 3 и более данных цифр можно считать проигрышной
                        int[] masivNeskolkihSovpadenuy = new int[6];
                        masivNeskolkihSovpadenuy[5] = 2; //@TODO по анологии выше - в целом мы еще в будущем будем использовать эту важную информацию ниже.
                        masivNeskolkihSovpadenuy[0] = vishuiPredentent[0];
                        masivNeskolkihSovpadenuy[1] = vishuiPredentent[1];
                        masivNeskolkihSovpadenuy[2] = vishuiPredentent[2];
                        masivNeskolkihSovpadenuy[3] = vishuiPredentent[3];
                        masivNeskolkihSovpadenuy[4] = vishuiPredentent[4];
                        listMasivovSovpadenuy.add(masivNeskolkihSovpadenuy);


                        List<Integer[]> combosToRemove = new ArrayList<>();
                        for (Integer[] combo : combinations) {
                            if (!Arrays.equals(combo, vishuiPredentent)) {
                                List<Integer> comboNums = Arrays.asList(combo);
                                int sameNumCount = 0;
                                for (int num : vishuiPredentent) {
                                    if (comboNums.contains(num)) {
                                        sameNumCount++;
                                    }
                                }
                                if (sameNumCount >= 3) { //@TODO по анологии выше
                                    combosToRemove.add(combo);
                                }
                            }
                        }
                        combinations.removeAll(combosToRemove);
                    }

                    if (count == 3) { //@TODO 3 совпадения говорит о том что любая комбинация имеющая 4ре совпадения редко но быть может и такое это всеже весомые числа из списка - проигрышна.
                        int[] masivNeskolkihSovpadenuy = new int[6];
                        masivNeskolkihSovpadenuy[5] = 3; //@TODO 5 индекс хранит в себе число совпадений с
                        masivNeskolkihSovpadenuy[0] = vishuiPredentent[0];
                        masivNeskolkihSovpadenuy[1] = vishuiPredentent[1];
                        masivNeskolkihSovpadenuy[2] = vishuiPredentent[2];
                        masivNeskolkihSovpadenuy[3] = vishuiPredentent[3];
                        masivNeskolkihSovpadenuy[4] = vishuiPredentent[4];
                        listMasivovSovpadenuy.add(masivNeskolkihSovpadenuy);

                        List<Integer[]> combosToRemove = new ArrayList<>();
                        for (Integer[] combo : combinations) {
                            if (!Arrays.equals(combo, vishuiPredentent)) {
                                List<Integer> comboNums = Arrays.asList(combo);
                                int sameNumCount = 0;
                                for (int num : vishuiPredentent) {
                                    if (comboNums.contains(num)) {
                                        sameNumCount++;
                                    }
                                }
                                if (sameNumCount >= 4) {
                                    combosToRemove.add(combo);
                                }
                            }
                        }
                        combinations.removeAll(combosToRemove);
                    } //@TODO все по анологии выше


                    if (count == 4) {
                        int[] masivNeskolkihSovpadenuy = new int[6];
                        masivNeskolkihSovpadenuy[5] = 4; //@TODO 5 индекс хранит в себе число совпадений с
                        masivNeskolkihSovpadenuy[0] = vishuiPredentent[0];
                        masivNeskolkihSovpadenuy[1] = vishuiPredentent[1];
                        masivNeskolkihSovpadenuy[2] = vishuiPredentent[2];
                        masivNeskolkihSovpadenuy[3] = vishuiPredentent[3];
                        masivNeskolkihSovpadenuy[4] = vishuiPredentent[4];
                        listMasivovSovpadenuy.add(masivNeskolkihSovpadenuy); //@TODO по анологии выше только сбор цееной информации


                    }


                    System.out.println("Высший прединтент: " + Arrays.toString(vishuiPredentent));


                    combinations.remove(vishuiPredentent); //@TODO удаляем высщий придендент из комбинации так как информацию при помощи него мы всю нужную уже получили - а он см проигрышный
                    vishuiPredentent = combinations.get(0);//@TODO его место занимает следующий с верху по списку

                    System.out.println("Он был удален");
                    System.out.println("Высший прединтент: " + Arrays.toString(vishuiPredentent));
                } else if (count == 0) { //@TODO  было три условия - см. выше =  5ть совпадений,    1,2,3,4 совпадения и самое интересное это 0 совпадений  - это оно. Любая комбинация содержащая хоть одну цифру из вышего претендента - являеться проигрышной
                    for (int i = 0; i < 5; i++) {
                        int num = vishuiPredentent[i]; //@TODO берем одну из 5ти цифр из вышего притендента для сравнения с цифрами всех комбинаций
                        Iterator<Integer[]> iterator = combinations.iterator();

                        while (iterator.hasNext()) {
                            Integer[] combo = iterator.next(); //@TODO поочередно проверям каждую комбинацию на начальном уровне например из 99 тогда

                            if (!Arrays.equals(combo, vishuiPredentent) && Arrays.asList(combo).contains(num)) { //@TODO проверяем если комбинация не сам высший предендент и есть совпадение с int num  - одна из цифр высшего придендента - на кадой итерации цикла  for меняеться
                                iterator.remove(); //@TODO в случае совпадения удаляем эту комбинацию
                            }
                        }

                        System.out.println("Были удалены все комбинации, содержащие цифру " + num); //@TODO  закончиналь одна итерация цикла for

                    }
                    combinations.remove(vishuiPredentent); //@TODO  удаляем высший придендент - так как тут совпадений нет это число нас не интересует для дальнейшего анализа -его мы не собираем в  masivNeskolkihSovpadenuy


//@TODO  этап номер 2 новая сортировка

                    for (Integer[] combo : combinations) { //@TODO  для отладки вывод всего списка без сортировки после  редактирования(удалений заранее проигрышных комбинаций)
                        for (int num : combo) {
                            System.out.print(num + " ");
                        }
                        System.out.println();
                    }

                    System.out.println("Новая сортировка");


                    Collections.sort(combinations, (combo1, combo2) -> {
                        int count1 = 0, count2 = 0;
                        for (int i = 0; i < 5; i++) {
                            count1 += counts[combo1[i]];
                            count2 += counts[combo2[i]];
                        }
                        return count2 - count1; //@TODO сортировка из оставшихся чисел по анологии выше - в большенстве случаев не добовляет эффективности так как комбинации  лишь удалялись и не меняли свой порядок но иногда редко удаляються такие комбинации что сортировка просто необходима - и наче без нее может выйти за приделы 10 попыток.
                    });


                    // ниже  не обязательный код  - который понизил среднеее арефмитическое количество попыток на одну игру с 6.3 до 5.7 при таком-же выйгрыше  в 100% случаев


                    if (listMasivovSovpadenuy.size() > 0) {
                        boolean breaks = false;
                        for (Integer[] combo : combinations) {  //@TODO перебираем оставшиеся комбинации в списке в цикле
                            int metkaPravdy = 0;
                            for (int i = 0; i < listMasivovSovpadenuy.size(); i++) { //@TODO вложенный цикл - берем теперь  поочереди каждую комбинацию где были совпадения ранее - что бы сравнить их все с данной комбинацией на этой итерации из оставшегося списка - логика проста  выйгрышная комбинация должна иметь не меньшее количество совпадений чисел со списком listMasivovSovpadenuy - сколько оно имело выше при проверке в предыдущих попытках - ведь listMasivovSovpadenuy это числа из предыдущих попыток с указанием количества совпадений в них


                                int[] masiv = listMasivovSovpadenuy.get(i);
                                int kolSofpadenui = masiv[5]; //@TODO из комбинации вытаскиваем число - количество совпадений ранее см. выше
                                int countsss = 0;

                                for (int p = 0; p < 5; p++) {
                                    if (Arrays.asList(combo).contains(masiv[p])) { //@TODO еще один вложеный во вложеный цикл - проверяем одну комбинацию из оставшегося списка на совпадение с нашей сохраненной комбинацие в который было  одно , два , три или четыри совпадения - информация об этом у нас храниться в int kolSofpadenui см выше
                                        countsss++; //@TODO здесь считаем количество совпадений у данной комбинации в этой этерации из списка
                                    }
                                }
                                if (countsss >= kolSofpadenui) {//@TODO  сравниваем у непроигрышноой комбинации должно быть совпадений не менее чем раньше - int kolSofpadenui
                                    metkaPravdy++; //@TODO  помечает что данная комьинация combo - прошла проверку по количеству совпадений одной из комбинаций listMasivovSovpadenuy
                                }

                                if (metkaPravdy == listMasivovSovpadenuy.size()) { //@TODO высший притендет vishuiPreds в данном случае выйгрышная коомбинация  - будет присвоен только той комбинации из combo - которая пройдет все проверки из списка listMasivovSovpadenuy - который кокраз таки и был сохранен для этого маловероятного случая около 2 % когда мы не смогли угадать число с 9 попыток
                                    vishuiPredentent = combo;
                                    breaks = true;


                                }
                            }
                            if (breaks) {
                                break;
                            }
                        }
                    }


                    //  выше не обязательный код  - который понизил среднеее арефмитическое количество попыток на одну игру с 6.3 до 5.7 при таком-же выйгрыше  в 100% случаев


                    else {
                        vishuiPredentent = combinations.get(0); //@TODO  теперь берем новый высший притендент на основе данной сортировки - да помойму она безполезна без преоценки ценности чисел на основе нового списка - так как остаеться то же порядок   ?????????????
                        System.out.println("Высший прединтент: " + Arrays.toString(vishuiPredentent));
                    }
                }


            }
            //@TODO  выше проходили циклы попыток с 1ой по 9ую


            poputka = 9; //@TODO это утверждение основано на 95ой строке приложения -  while (count < 5 && poputka < 10) { тоесть выйдя из этого цикла у нас однозначно - poputka = 9;


            if (ugadalis9) { //@TODO  10ая интерестная попытка - до которой доходит очень редко но шанса промазать нету

//@TODO 10ая попытка  - Отладка
                System.out.println("Высший прединтент: " + Arrays.toString(vishuiPredentent));

                for (Integer[] combo : combinations) {
                    for (int num : combo) {
                        System.out.print(num + " ");  //@TODO выводим весь оставщийся список в консоль для отладки
                    }
                    System.out.println();
                }
                System.out.println("Это были оставшиеся числа ***************************************************************************");

                for (int[] combo1 : listMasivovSovpadenuy) {
                    for (int num : combo1) {
                        System.out.print(num + " "); //@TODO выводи в консоль наш лист комбинаций с анализа собранный выше для отладки
                    }
                    System.out.println();
                }


                System.out.println("Это были комбинации ддля анализа");


                System.out.println("А вот и загаданая кмбинацияяя");

                for (int num : zagadanayaCombinaciya) {
                    System.out.print(num + " ");
                }
                System.out.println();


                //@TODO Логика 10ой поытки
                System.out.println("Логика 10ой поытки ***************************************************");


                Integer[] vishuiPreds = null; //@TODO новая переменная под высщий придендент - что бы не путаться в коде


                for (Integer[] combo : combinations) {  //@TODO перебираем оставшиеся комбинации в списке в цикле
                    int metkaPravdy = 0;
                    for (int i = 0; i < listMasivovSovpadenuy.size(); i++) { //@TODO вложенный цикл - берем теперь  поочереди каждую комбинацию где были совпадения ранее - что бы сравнить их все с данной комбинацией на этой итерации из оставшегося списка - логика проста  выйгрышная комбинация должна иметь не меньшее количество совпадений чисел со списком listMasivovSovpadenuy - сколько оно имело выше при проверке в предыдущих попытках - ведь listMasivovSovpadenuy это числа из предыдущих попыток с указанием количества совпадений в них


                        int[] masiv = listMasivovSovpadenuy.get(i);
                        int kolSofpadenui = masiv[5]; //@TODO из комбинации вытаскиваем число - количество совпадений ранее см. выше
                        int countsss = 0;


                        for (int p = 0; p < 5; p++) {
                            if (Arrays.asList(combo).contains(masiv[p])) { //@TODO еще один вложеный во вложеный цикл - проверяем одну комбинацию из оставшегося списка на совпадение с нашей сохраненной комбинацие в который было  одно , два , три или четыри совпадения - информация об этом у нас храниться в int kolSofpadenui см выше
                                countsss++; //@TODO здесь считаем количество совпадений у данной комбинации в этой этерации из списка
                            }
                        }
                        if (countsss >= kolSofpadenui) {//@TODO  сравниваем у непроигрышноой комбинации должно быть совпадений не менее чем раньше - int kolSofpadenui
                            metkaPravdy++; //@TODO  помечает что данная комьинация combo - прошла проверку по количеству совпадений одной из комбинаций listMasivovSovpadenuy
                        }

                        if (metkaPravdy == listMasivovSovpadenuy.size()) { //@TODO высший притендет vishuiPreds в данном случае выйгрышная коомбинация  - будет присвоен только той комбинации из combo - которая пройдет все проверки из списка listMasivovSovpadenuy - который кокраз таки и был сохранен для этого маловероятного случая около 2 % когда мы не смогли угадать число с 9 попыток

                            vishuiPreds = combo;//@TODO присвоение данной комбинации высшего притендента


                            int countyy = 0;
                            for (int iii = 0; iii < 5; iii++) {
                                System.out.println("Сейчас будет сравниваться выший преденнт со всеми метками правды");
                                System.out.println(Arrays.toString(vishuiPreds));
                                if (Arrays.asList(vishuiPreds).contains(zagadanayaCombinaciya[iii])) { //@TODO  сравниваем высший претендент с каждым числом из загаданной комбинации - выигрываем в 100% случаев
                                    countyy++;
                                }

                            }


                            System.out.println("Количество совпадений: " + count);
                            poputka++;


                            if (countyy == 5) { //@TODO когда у нас совпали все пять цифр высшего притендента и загаданой комбинации - игра считаеться выйграной

                                System.out.println("Вы выиграли! Количество попыток: " + poputka);
                                System.out.println(Arrays.toString(zagadanayaCombinaciya));

                                summaVsehPoputokZaVsyuIgru = summaVsehPoputokZaVsyuIgru + poputka;

                                if (igra == 1000) {
                                    System.out.println("Сумма всех попыток " + summaVsehPoputokZaVsyuIgru);
                                }


                                if (poputka <= 10) { //@TODO  успешной считаеться игра только которая уложилась в 10ть попыток
                                    successfulAttempts++; //@TODO  переменная хранащая в себе количестов выйгранных игр по умолчанию из 1000
                                } else {
                                    //@TODO  отладочная информация
                                    System.out.println("Igra ostanovilas");
                                }
                            }


                        }
                    }

                } //@TODO  цикл 346 строка  for (Integer[] combo : combinations)  - заканчиваеться тут
            }


            for (int[] combo1 : listMasivovSovpadenuy) {
                for (int num : combo1) {
                    System.out.print(num + " "); //@TODO  лист наших оставшихся чисел
                }
                System.out.println();
            }

            System.out.println("Это были комбинации для анализа");


            if (poputka >= 11) {
                System.out.println("poputka exeption 11");
                break;
                //@TODO  выход, если больше не осталось попыток
            }

            //@TODO скобка цикла for 10000


        }


        System.out.println("Количество удачных игр по умолчанию из 1000: " + successfulAttempts);


    }


    public static class Count {
        private int num; //@TODO  само двухзначное число
        private int count; //@TODO количество таких чисел встреченных в списке из 100 комбинаций

        public Count(int num, int count) {
            this.num = num;
            this.count = count;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }
    }
}












