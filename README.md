# Codingame-Code4Life

I solve this problem with Scala because it is very similar to Java.

I create best sample object that relative to health. I construct my strategy with health value.

If best sample not carried by my robot, then my robot go-to diagnosis.

After the diagnosis module, robot connect with best sample(which has 0 or null values in the begining) or another say best sample id(relative with health)

If that best sample is not null(it means my robot Connected with some sample) then robot go-to Molecules module.

If collected best sample costs values (by index) greater than my robot's storages values (by index storageA,storageB,storageC,storageD,storageE) then the molecule(neededMol) that has to collect will be one of the "ABCDE"(by index)

When my robot collect samples then go to Laboratory module and Connect it with best sample id(that id is equal to id that when my robot is in the Diagnosis module)
